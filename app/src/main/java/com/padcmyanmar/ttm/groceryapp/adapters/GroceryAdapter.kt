package com.padcmyanmar.ttm.groceryapp.adapters

import android.util.Log
import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO



import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.ttm.groceryapp.delegates.GroceryViewItemActionDelegate

import com.padcmyanmar.ttm.groceryapp.viewholders.GroceryViewHolder



class GroceryAdapter(private val mDelegate: GroceryViewItemActionDelegate) : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {
    companion object{
       private const val VIEW_TYPE_LINEAR = 0
       private const val VIEW_TYPE_GRID = 1
    }
 private var changeLayoutFlag = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
    Log.d("GroceryAdapter","$changeLayoutFlag")
      when(changeLayoutFlag)
       {
          VIEW_TYPE_LINEAR->{
             val view =   LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)

              return GroceryViewHolder(view, mDelegate)
          }
          VIEW_TYPE_GRID->{
             val view =   LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item_vertical,parent,false)

              return GroceryViewHolder(view, mDelegate)
          }
          else->{
            val  view =   LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)

              return GroceryViewHolder(view, mDelegate)
          }

       }



    }

    fun setLayoutFlag(changeLayoutFlagParam: Long) {
        changeLayoutFlag = changeLayoutFlagParam.toInt()
    }
}