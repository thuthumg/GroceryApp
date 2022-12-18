package com.padcmyanmar.ttm.groceryapp.network

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO

import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

object CloudFirestoreFirebaseApiImpl : FirebaseApi{

  val db = Firebase.firestore
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private val storageReference: StorageReference = storage.reference
    var returnUrlString:String? = ""
    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        db.collection("groceries")

            .addSnapshotListener { value, error ->

                error?.let {
                    onFialure(it.message ?: "Please check connection")
                }?: run {
                    val groceriesList: MutableList<GroceryVO> = arrayListOf()
                    val result : List<DocumentSnapshot> = value?.documents ?: arrayListOf()
                    for(document : DocumentSnapshot in result)
                    {
                        val data = document.data
                        var grocery = GroceryVO()
                        grocery.name = data?.get("name") as String
                        grocery.description =  data["description"] as String
                        grocery.amount =  (data["amount"] as Long).toInt()
                        grocery.image = data["image"] as String?
                        groceriesList.add(grocery)

                    }
                    onSuccess(groceriesList)
                }




            }
          /*  .addOnSuccessListener { result ->
                val groceriesList: MutableList<GroceryVO> = arrayListOf()

                for(document in result)
                {
                    val data = document.data
                    var grocery = GroceryVO()
                    grocery.name = data?.get("name") as String
                    grocery.description =  data["description"] as String
                    grocery.amount =  (data["amount"] as Long).toInt()

                    groceriesList.add(grocery)

                }
                onSuccess(groceriesList)
            }

            .addOnFailureListener {
                onFialure(it.message ?: "Please check connection")
            }
*/

        /*.addOnCompleteListener { result ->
                val groceriesList: MutableList<GroceryVO> = arrayListOf()

               for(document in result.result.documents)
               {
                   val data = document.data
                   var grocery = GroceryVO()
                   grocery.name = data?.get("name") as String
                   grocery.description =  data["description"] as String
                   grocery.amount =  (data["amount"] as Long).toInt()

                   groceriesList.add(grocery)

               }
                onSuccess(groceriesList)
            }*/


    }
    override fun addGrocery(name: String, description: String, amount: Int, image: String) {
        val groceryMap : HashMap<String,Any> = hashMapOf(
            "name" to name,
            "description" to description,
            "amount" to amount.toLong(),
            "image" to image
        )

        db.collection("groceries")
            .document(name)
            .set(groceryMap)
            .addOnSuccessListener {
                Log.d("Success","Successfully added grocery")
            }
            .addOnFailureListener {
                Log.d("Failure","Failed to add grocery")
            }
    }

    override fun deleteGrocery(name: String) {
       db.collection("groceries")
           .document(name)
           .delete()
           .addOnSuccessListener {
               Log.d("Success","Successfully deleted grocery")
           }
           .addOnFailureListener {
               Log.d("Failure","Failed to delete grocery")
           }
    }

    override fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO,onSuccess: (returnUrlString: String?) -> Unit) {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100,baos)
        val data: ByteArray = baos.toByteArray()

        val imageRef: StorageReference = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask: UploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener{

        }.addOnSuccessListener {
        }

        val urlTask: Task<Uri> = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task->
            val imageUrl : String? = task.result?.toString()
            returnUrlString = imageUrl
            addGrocery(
                grocery.name ?: "",
                grocery.description ?: "",
                grocery.amount ?: 0,
                imageUrl ?: ""
            )

            onSuccess(returnUrlString)

        }
    }

}