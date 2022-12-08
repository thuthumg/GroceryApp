package com.padcmyanmar.ttm.groceryapp.network

import android.graphics.Bitmap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO


object RealtimeDatabaseFirebaseApiImpl  : FirebaseApi {

    private val database: DatabaseReference = Firebase.database.reference

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

    override fun addGrocery(name: String, description: String, amount: Int, image: String) {
        TODO("Not yet implemented")
    }

    override fun deleteGrocery(name: String) {
        TODO("Not yet implemented")
    }

    override fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO) {
        TODO("Not yet implemented")
    }
}