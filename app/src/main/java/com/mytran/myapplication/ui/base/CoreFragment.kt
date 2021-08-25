package com.mytran.myapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class CoreFragment: Fragment() {
    abstract fun getLayoutId(): Int
    abstract fun initObserver()
    abstract fun defaultData()
    abstract fun doingActionClick(data: Any?, action: String)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onResume() {
        super.onResume()
        defaultData()
        initObserver()
    }
}