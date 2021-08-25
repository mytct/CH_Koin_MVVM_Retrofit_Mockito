package com.mytran.myapplication.ui.main

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytran.myapplication.R
import com.mytran.myapplication.ui.base.CoreFragment
import com.mytran.myapplication.ui.base.TypeCoreAction
import com.mytran.myapplication.ui.main.adapter.CoinAdapter
import com.mytran.myapplication.ui.main.data.ItemCoinData
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : CoreFragment() {
    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var coinAdapter: CoinAdapter
    private val homeViewModel: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.main_fragment
    override fun defaultData() {
        homeViewModel.getDefaultCoinList()
    }
    override fun initObserver() {
        homeViewModel.getCoinList()
        homeViewModel.coinList.observe(viewLifecycleOwner, { coinList ->
            Log.v("MainFragment", "coinList $coinList")
            initAdapter(coinList)
        })
    }

    private fun initAdapter(coinList: MutableList<ItemCoinData>) {
        coinAdapter = CoinAdapter(coinList) { data, action->
            doingActionClick(data, action)
        }
        rvData?.run {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun doingActionClick(data: Any?, action: String) {
        data?.apply {
            when(action) {
                TypeCoreAction.ADD_COIN-> {

                }
                TypeCoreAction.REMOVE_COIN-> {

                }
            }
        }
    }
}