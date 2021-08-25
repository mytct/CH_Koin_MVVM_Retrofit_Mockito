package com.mytran.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytran.myapplication.api.repository.CoinRepository
import com.mytran.myapplication.ui.main.data.ItemCoinData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(private val coinRepository: CoinRepository) : ViewModel() {
    val coinList = MutableLiveData<MutableList<ItemCoinData>>()
    private val errorMessage = MutableLiveData<String>()
    fun getDefaultCoinList() {
        //init empty values
        coinList.postValue(mutableListOf(ItemCoinData.ItemLoading()))
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
                            coinList.postValue(result.list.map { ItemCoinData.ItemCoinDisplay(it) }.toMutableList())
                        }
                    }
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            errorMessage.postValue("Network Error")
                        }
                        is HttpException -> {
                            val codeError = throwable.code()
                            val errorMessageResponse = throwable.message()
                            errorMessage.postValue("Error $errorMessageResponse : $codeError")
                        }
                        else -> {
                            errorMessage.postValue("Uknown error")
                        }
                    }
                }
            }
        }
    }
}