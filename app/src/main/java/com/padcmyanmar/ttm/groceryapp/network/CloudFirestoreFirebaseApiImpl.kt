package com.padcmyanmar.ttm.groceryapp.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO

object CloudFirestoreFirebaseApiImpl : FirebaseApi{

  val db = Firebase.firestore

    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        db.collection("groceries")
            .get()
            .addOnSuccessListener { result ->
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

    override fun addGrocery(name: String, description: String, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteGrocery(name: String) {
        TODO("Not yet implemented")
    }
}