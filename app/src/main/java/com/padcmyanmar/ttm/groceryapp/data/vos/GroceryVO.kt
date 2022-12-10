package com.padcmyanmar.ttm.groceryapp.data.vos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class GroceryVO(
    var name: String? = "",
    var description: String? = "",
    var amount: Int? = 0
)