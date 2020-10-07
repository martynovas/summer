package com.example.model

import java.lang.Exception

data class Account(
    val amount: Money,
    val client: Client,
    val isDefault: Boolean
){
    operator fun plusAssign(b: Money) {
        if (this.amount.currency != b.currency)
            throw Exception("Currency not equal")

        this.amount += b
    }

    operator fun minusAssign(b: Money) {
        if (this.amount.currency != b.currency)
            throw Exception("Currency not equal")

        this.amount -= b
    }

}
