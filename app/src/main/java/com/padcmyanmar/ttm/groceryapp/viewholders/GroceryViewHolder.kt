package com.padcmyanmar.ttm.groceryapp.viewholders

import android.view.View
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import kotlinx.android.synthetic.main.view_holder_grocery_item.view.*

class GroceryViewHolder(itemView: View) : BaseViewHolder<GroceryVO>(itemView) {

    override fun bindData(data: GroceryVO) {
        itemView.tvTitle.text = data.name
        itemView.tvDescription.text = data.description
        itemView.tvCount.text = "x ${data.amount.toString()}"
    }
}