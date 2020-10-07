package com.example.model

import java.lang.Exception
import java.math.BigDecimal

data class Money(
    var value: BigDecimal,
    val currency: Int,
) {
    operator fun plusAssign(b: Money) {
        if (this.currency != b.currency)
            throw Exception("Currency not equal")

        this.value += b.value
    }

    operator fun minusAssign(b: Money) {
        if (this.currency != b.currency)
            throw Exception("Currency not equal")

        this.value -= b.value
    }
}
