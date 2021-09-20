package com.mytran.myapplication.ui.main

import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytran.myapplication.R
import com.mytran.myapplication.ui.base.CoreFragment
import com.mytran.myapplication.ui.base.TypeCoreAction
import com.mytran.myapplication.ui.main.adapter.CoinAdapter
import com.mytran.myapplication.ui.main.data.ItemCoinData
import com.mytran.myapplication.utils.launchPeriodicAsync
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : CoreFragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var job: Deferred<Unit>
    private lateinit var coinAdapter: CoinAdapter

    private val homeViewModel: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.main_fragment
    override fun defaultData() {
        homeViewModel.getDefaultCoinList()
    }

    override fun initObserver() {
        initIntervalFetch()
        homeViewModel.coinList.observe(viewLifecycleOwner, { coinList ->
            Log.v("MainFragment", "coinList $coinList")
            initAdapter(coinList)
        })
    }

    private fun initIntervalFetch() {
        Log.v("MainFragment", "initIntervalFetch resume")
        job = CoroutineScope(Dispatchers.IO).launchPeriodicAsync(30000) {
            homeViewModel.getCoinList()
        }
    }

    private fun test() {}
    private fun testCodeReview() {
        val numb: String? = null
        edtSearch.setText(numb)
    }

    override fun onPause() {
        Log.v("MainFragment", "initIntervalFetch stop")
        super.onPause()
        job.cancel()
    }

    override fun refreshLayout() {
        edtSearch?.doAfterTextChanged {
            homeViewModel.filterCoinViaText(it.toString())
        }
        btnClear?.setOnClickListener {
            edtSearch?.setText("")
            homeViewModel.getCoinList()
        }
    }

    private fun initAdapter(coinList: MutableList<ItemCoinData>) {
        if(::coinAdapter.isInitialized) {
            coinAdapter.refreshData(coinList)
            coinAdapter.notifyDataSetChanged()
        } else {
            coinAdapter = CoinAdapter { data, action->
                doingActionClick(data, action)
            }
            coinAdapter.refreshData(coinList)
            rvData?.run {
                adapter = coinAdapter
                layoutManager = LinearLayoutManager(context)
            }
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