package com.mytran.myapplication.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mytran.myapplication.api.repository.CoinRepository
import com.mytran.myapplication.api.response.CoinData
import com.mytran.myapplication.di.apiModule
import com.mytran.myapplication.di.repositoryModule
import com.mytran.myapplication.di.retrofitModule
import com.mytran.myapplication.di.viewModelModule
import com.mytran.myapplication.ui.main.data.ItemCoinData
import junit.framework.TestCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest: KoinTest {
    private val viewModel: MainViewModel by inject()
    private val coinRepository: CoinRepository by inject()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @After
    fun stopKoinAfterTest() = stopKoin()

    @Mock
    lateinit var observer: Observer<List<ItemCoinData>>

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(
                //databaseModule,
                viewModelModule,
                apiModule,
                repositoryModule,
                retrofitModule
            ))
        }

        viewModel.coinList.observeForever(observer)
    }

    @Test
    fun testListCoin_ViewModel() {
        GlobalScope.launch {
            val fakeData = listOf(
                CoinData(
                    "LTC",
                    "USD",
                    "172.497",
                    "171.745",
                    "https://cdn.coinhako.com/assets/wallet-ltc-e4ce25a8fb34c45d40165b6f4eecfbca2729c40c20611acd45ea0dc3ab50f8a6.png",
                    "Litecoin"
                )
            )
            val fakeResult = fakeData.map { ItemCoinData.ItemCoinDisplay(it) }
            val fakeResponse = MutableLiveData<List<ItemCoinData>>()
            fakeResponse.value = fakeResult
            Mockito.`when`(coinRepository.repoGetListCoins()).then {
                fakeResponse
            }
            Mockito.verify(observer).onChanged(fakeResult)
        }
    }
}