package com.mytran.myapplication.ui.main.VH

import android.view.View
import com.mytran.myapplication.api.response.CoinData
import com.mytran.myapplication.ui.base.CoreAdapter
import com.mytran.myapplication.ui.base.OnClickWithCallback
import com.mytran.myapplication.utils.loadImage
import kotlinx.android.synthetic.main.item_coin.view.*

class ItemCoinDisplayVH(view: View) : CoreAdapter.CoreViewHolder(view) {
    fun bindData(data: CoinData, listener: OnClickWithCallback) {
        with(itemView) {
            imAvatar?.loadImage(data.icon, avatarSize) {}
            tvTitle?.text = data.name
            tvDesc?.text = data.base
            tvPriceBuy?.text = StringBuilder().append("+").append(data.buy_price).toString()
            tvPriceSell?.text = StringBuilder().append("-").append(data.sell_price).toString()
        }
    }
}