package com.wizeline.realtimestocks.util

import java.text.NumberFormat

fun Double.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}