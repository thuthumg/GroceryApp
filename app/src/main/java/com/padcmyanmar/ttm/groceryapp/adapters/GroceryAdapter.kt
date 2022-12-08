package com.padcmyanmar.ttm.groceryapp.adapters

import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO



import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.ttm.groceryapp.delegates.GroceryViewItemActionDelegate

import com.padcmyanmar.ttm.groceryapp.viewholders.GroceryViewHolder



class GroceryAdapter(private val mDelegate: GroceryViewItemActionDelegate) : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)
        return GroceryViewHolder(view, mDelegate)
    }
}