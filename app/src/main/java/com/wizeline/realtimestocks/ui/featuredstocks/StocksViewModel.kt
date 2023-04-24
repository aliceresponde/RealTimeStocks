package com.wizeline.realtimestocks.ui.featuredstocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.realtimestocks.stock.Stock
import com.wizeline.realtimestocks.stock.StocksRepository
import kotlinx.coroutines.launch

class StocksViewModel(
    private val repository: StocksRepository = StocksRepository.create()
) : ViewModel() {

    private val _stocks = MutableLiveData<List<Stock>>()
    lateinit var stocks: LiveData<List<Stock>>

    init {
        getStocks()
    }

    fun getStocks() {
        viewModelScope.launch {
            _stocks.value = repository.getFeaturedStocksAsLiveData(10000).value
        }
    }
}