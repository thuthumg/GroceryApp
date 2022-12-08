package com.padcmyanmar.ttm.groceryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.grocery.viewholders.GroceryViewHolder
import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO

class GroceryAdapter() : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {

    }
}