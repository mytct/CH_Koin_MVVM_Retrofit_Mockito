package com.mytran.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytran.myapplication.api.repository.CoinRepository
import com.mytran.myapplication.api.response.CoinResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(private val coinRepository: CoinRepository) : ViewModel() {
    val coinList = MutableLiveData<List<CoinResponse>>()
    private val errorMessage = MutableLiveData<String>()
    fun getCoinList () {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = coinRepository.repoGetListCoins()
                    coinList.postValue(result)
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