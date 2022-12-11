package com.padcmyanmar.ttm.groceryapp.network

import android.graphics.Bitmap
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import java.io.ByteArrayOutputStream
import java.util.UUID


object RealtimeDatabaseFirebaseApiImpl  : FirebaseApi {

    private val database: DatabaseReference = Firebase.database.reference
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageReference: StorageReference = storage.reference

    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        database.child("groceries").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFialure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val groceryList = arrayListOf<GroceryVO>()
                snapshot.children.forEach { dataSnapShot ->
                    dataSnapShot.getValue(GroceryVO::class.java)?.let {
                        groceryList.add(it)
                    }
                }
                onSuccess(groceryList)
            }
        })
    }
    override fun addGrocery(name: String, description: String, amount: Int, imageUrl: String) {

        database.child("groceries").child(name).
        setValue(GroceryVO(name,description,amount,imageUrl))
    }

    override fun deleteGrocery(name: String) {
        database.child("groceries").child(name).removeValue()
    }

    override fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO){
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100,baos)
        val data: ByteArray = baos.toByteArray()

        val imageRef: StorageReference = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask: UploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener{

        }.addOnSuccessListener {
        }

        val urlTask:Task<Uri> = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task->
            val imageUrl : String? = task.result?.toString()
            addGrocery(grocery.name ?: "",
            grocery.description ?: "",
            grocery.amount ?: 0,
            imageUrl ?: "")

        }

    }


    override fun getGroceriesByKey(
        name: String,
        onSuccess: (grocery:GroceryVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groceries").child(name).get().result.value.let {
            onSuccess(database.child("groceries").child(name).get().result.value as GroceryVO)
        }
        onFailure("Fail")

//        database.child("groceries").addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                onFialure(error.message)
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val groceryList = arrayListOf<GroceryVO>()
//                snapshot.children.forEach { dataSnapShot ->
//                    dataSnapShot.getValue(GroceryVO::class.java)?.let {
//                        groceryList.add(it)
//                    }
//                }
//                onSuccess(groceryList)
//            }
//        })
//
//
//      return

    }


}