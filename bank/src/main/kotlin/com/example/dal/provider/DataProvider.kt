package com.example.dal.provider

import com.example.model.Account
import com.example.model.Client
import com.example.model.Money

object DataProvider {
    val fromClient =  Client(1, "Superman")
    val toClient =   Client(2, "Dr Octopus")

    val accounts = mutableListOf<Account>(
        Account(
            Money(100000.toBigDecimal(), 643),
            fromClient,
            true
        ),
        Account(
            Money(100000.toBigDecimal(), 840),
            fromClient,
            false
        ),
        Account(
            Money(100000.toBigDecimal(), 643),
            toClient,
            true
        ),
        Account(
            Money(100000.toBigDecimal(), 840),
            toClient,
            false
        )
    )


}
