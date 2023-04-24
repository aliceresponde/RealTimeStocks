package com.wizeline.realtimestocks.stock

import kotlin.random.Random

class StocksGenerator {

    private val apple = Stock(
        ticker = "APPL",
        price = 120.0,
        companyName = "Apple"
    )

    private val alphabet = Stock(
        ticker = "GOOGL",
        price = 16_320.0,
        companyName = "Alphabet Inc"
    )

    private val tesla = Stock(
        ticker = "TSLA",
        price = 400.8,
        companyName = "Tesla"
    )

    private val walmart = Stock(
        ticker = "WMT",
        price = 144.50,
        companyName = "Walmart"
    )

    private val procter = Stock(
        ticker = "PG",
        price = 138.6,
        companyName = "Procter & Gamble"
    )

    private val microsoft = Stock(
        ticker = "MSFT",
        price = 216.81,
        companyName = "Microsoft"
    )

    private val alwaysShown = listOf(apple, alphabet, procter, tesla)

    private val maybeShow = listOf(microsoft, walmart)

    fun getStocks(): List<Stock> {
        val randomStocks = randomStocks()
        return randomStocks.plus(alwaysShown)
            .sortedBy { it.ticker }
            .mutate()
    }

    private fun List<Stock>.mutate(): List<Stock> = this.map {
        it.copy(
            price = it.price.mutate()
        )
    }

    private fun Double.mutate(): Double {
        val range = (this * 0.1) / 2
        return Random.nextDouble(this - range, this + range)
    }

    private fun randomStocks(): List<Stock> {
        return maybeShow.shuffled().take(Random.nextInt(maybeShow.size))
    }

    fun getStock(stockTicker: String): Stock? {
        return alwaysShown.firstOrNull { it.ticker == stockTicker }
            ?: maybeShow.firstOrNull { it.ticker == stockTicker }
    }
}