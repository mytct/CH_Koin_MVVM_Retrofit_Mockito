package com.mytran.myapplication.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CoreAdapter : RecyclerView.Adapter<CoreAdapter.CoreViewHolder>() {
    //this method is giving the size of the list
    override fun getItemCount(): Int = 0

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CoreViewHolder, position: Int) {}

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder = getBaseViewHolder(parent, viewType)!!
    fun createViewFromDynamicLayoutId(layoutId: Int, parent: ViewGroup): View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    open fun getBaseViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder? = null

    abstract class CoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarSize: Int = 100
    }
}

abstract class CallBackData (
    val data: Any? = null,
)