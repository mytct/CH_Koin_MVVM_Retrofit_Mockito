package com.mytran.myapplication.ui.base

import androidx.annotation.StringDef
import com.mytran.myapplication.R

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    TypeCoreAction.ADD_COIN,
    TypeCoreAction.REMOVE_COIN,
)
annotation class TypeCoreAction {
    companion object {
        const val ADD_COIN = "ADD_COIN"
        const val REMOVE_COIN = "REMOVE_COIN"
    }
}