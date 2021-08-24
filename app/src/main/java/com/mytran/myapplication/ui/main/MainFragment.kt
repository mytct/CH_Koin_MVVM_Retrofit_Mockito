package com.mytran.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.Observer
import com.mytran.myapplication.R
import com.mytran.myapplication.ui.base.CoreFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : CoreFragment() {
    companion object {
        fun newInstance() = MainFragment()
    }
    private val homeViewModel: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.main_fragment
    override fun initObserver() {
        homeViewModel.getCoinList()
        homeViewModel.coinList.observe(viewLifecycleOwner, { coinList ->
            Log.v("MainFragment", "coinList $coinList")
        })
    }
}