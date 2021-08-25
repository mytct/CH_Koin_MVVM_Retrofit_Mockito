package com.mytran.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytran.myapplication.api.repository.CoinRepository
import com.mytran.myapplication.ui.main.data.ItemCoinData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel(private val coinRepository: CoinRepository) : ViewModel() {
    val coinList = MutableLiveData<MutableList<ItemCoinData>>()
    private val dataSet = mutableListOf<ItemCoinData>()
    fun getDefaultCoinList() {
        //init empty values
        coinList.postValue(mutableListOf(ItemCoinData.ItemLoading()))
    }

    fun filterCoinViaText(keyword: String) {
        Log.v("MainViewModel", "filterCoinViaText $keyword")
        val tmp = mutableListOf<ItemCoinData>().apply { addAll(dataSet) }
        Log.v("MainViewModel", "filterCoinViaText size ${tmp.size}")
        Log.v("MainViewModel", "filterCoinViaText dataSet ${dataSet.size}")
        if(keyword.isBlank()) coinList.postValue(tmp.toMutableList())
        else coinList.postValue(tmp.filterIsInstance<ItemCoinData.ItemCoinDisplay>().filter { it.data.name.lowercase(
            Locale.getDefault()
        )
            .contains(keyword) || it.data.base.lowercase(Locale.getDefault()).contains(keyword) }.toMutableList())
    }

    fun getCoinList () {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = coinRepository.repoGetListCoins()
                    result.list.apply {
                        if(isEmpty()) {
                            coinList.postValue(mutableListOf(ItemCoinData.ItemEmpty()))
                        } else {
                            val response = result.list.map { ItemCoinData.ItemCoinDisplay(it) }
                            coinList.postValue(response.toMutableList())
                            //update cache
                            dataSet.clear()
                            dataSet.addAll(response)
                        }
                    }
                } catch (throwable: Throwable) {
                    //init empty values
                    coinList.postValue(mutableListOf(ItemCoinData.ItemEmpty()))
                }
            }
        }
    }
}