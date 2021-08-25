package com.mytran.myapplication.ui.main.adapter

import android.view.ViewGroup
import com.mytran.myapplication.ui.base.CoreAdapter
import com.mytran.myapplication.ui.base.OnClickWithCallback
import com.mytran.myapplication.ui.main.VH.ItemCoinDisplayVH
import com.mytran.myapplication.ui.main.VH.ItemCoinEmptyVH
import com.mytran.myapplication.ui.main.VH.ItemCoinLoadingVH
import com.mytran.myapplication.ui.main.data.ItemCoinData
import com.mytran.myapplication.ui.main.data.TypeCoinGeneric

class CoinAdapter(
    private val listener: OnClickWithCallback
) : CoreAdapter(

) {
    private val data: MutableList<ItemCoinData> = mutableListOf()
    fun refreshData(list: MutableList<ItemCoinData>) {
        data.clear()
        data.addAll(list)
    }
    override fun getItemId(position: Int): Long = data[position].hashCode().toLong()
    override fun getItemCount(): Int = data.size
    override fun getItemViewType(position: Int): Int = data[position].type
    override fun onBindViewHolder(holder: CoreViewHolder, position: Int) {
        val child = data[position]
        when (holder) {
            is ItemCoinDisplayVH -> {
                val data = (child as ItemCoinData.ItemCoinDisplay).data
                holder.bindData(data, listener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder {
        val view = createViewFromDynamicLayoutId(viewType, parent)
        return when (viewType) {
            TypeCoinGeneric.ITEM_COIN_DISPLAY -> ItemCoinDisplayVH(view)
            TypeCoinGeneric.ITEM_LOADING -> ItemCoinLoadingVH(view)
            else -> ItemCoinEmptyVH(view)
        }
    }
}