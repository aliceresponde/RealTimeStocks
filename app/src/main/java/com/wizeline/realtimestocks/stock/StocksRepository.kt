package com.wizeline.realtimestocks.stock

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import io.reactivex.Observable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.rx2.asObservable

class StocksRepository(
    private val dispatcher: CoroutineDispatcher,
    private val generator: StocksGenerator
) {

    companion object {
        @JvmStatic
        fun create() = StocksRepository(Dispatchers.IO, StocksGenerator())
    }

    /**
     * Closes repository's [CoroutineDispatcher].
     */
    fun close() {
        dispatcher.cancel()
    }

    /**
     * Gets featured stocks.
     *
     * @return list of featured stocks sorted by ticker.
     */
    fun getFeaturedStocks(): List<Stock> {
        return generator.getStocks()
    }

    /**
     * Gets featured stocks.
     *
     * @param delayInMillis applied to resulting flow
     * @return flow containing a list of featured stocks sorted by ticker.
     */
    @ExperimentalCoroutinesApi
    fun getFeaturedStocksAsFlow(delayInMillis: Long = 3000): Flow<List<Stock>> {
        return channelFlow {
            while (!isClosedForSend) {
                val stocks = getFeaturedStocks()
                send(stocks)
                delay(delayInMillis)
            }
        }.flowOn(dispatcher)
    }

    /**
     * Gets featured stocks as LiveData
     *
     * @param delayInMillis applied to resulting LiveData
     * @return LiveData containing a list of featured stocks sorted by ticker.
     */
    @ExperimentalCoroutinesApi
    fun getFeaturedStocksAsLiveData(delayInMillis: Long = 3000): LiveData<List<Stock>> {
        return getFeaturedStocksAsFlow(delayInMillis).asLiveData()
    }

    /**
     * Gets featured stocks as Observable
     *
     * @param delayInMillis applied to resulting Observable
     * @return Observable containing a list of featured stocks sorted by ticker.
     */
    @ExperimentalCoroutinesApi
    fun getFeaturedStocksAsObservable(delayInMillis: Long = 3000): Observable<List<Stock>> {
        return getFeaturedStocksAsFlow(delayInMillis).asObservable()
    }
}
