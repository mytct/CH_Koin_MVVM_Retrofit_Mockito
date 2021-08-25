package com.mytran.myapplication.ui.main.data

import androidx.annotation.IntDef
import com.mytran.myapplication.R
import com.mytran.myapplication.api.response.CoinData

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    TypeCoinGeneric.ITEM_EMPTY,
    TypeCoinGeneric.ITEM_LOADING,
    TypeCoinGeneric.ITEM_COIN_DISPLAY,
)
annotation class TypeCoinGeneric {
    companion object {
        const val ITEM_EMPTY = R.layout.item_coin_empty
        const val ITEM_LOADING = R.layout.item_coin_loading
        const val ITEM_COIN_DISPLAY = R.layout.item_coin
    }
}

@TypeCoinGeneric
sealed class ItemCoinData(val type: Int) {
    class ItemEmpty : ItemCoinData(TypeCoinGeneric.ITEM_EMPTY)
    class ItemLoading : ItemCoinData(TypeCoinGeneric.ITEM_LOADING)
    class ItemCoinDisplay(val data: CoinData) : ItemCoinData(TypeCoinGeneric.ITEM_COIN_DISPLAY)
}