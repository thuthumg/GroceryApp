package com.padcmyanmar.ttm.groceryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected var mData: W? = null
    abstract fun bindData(data: W)

}
