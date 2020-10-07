package com.example

import com.example.context.support.GenericApplicationContext
import com.example.dal.provider.DataProvider
import com.example.model.Money
import com.example.service.AccountService
import com.example.service.BankConfig
import com.example.service.BankService
import com.example.service.DboService

class MainClass {
}

fun main(){
    val context = GenericApplicationContext(BankConfig())
    val dboService = context.getBean(DboService::class)
    val accountService = context.getBean(AccountService::class)

    val accountFrom = accountService.getDefaultAccount(DataProvider.fromClient)
    val accountTo = accountService.getDefaultAccount(DataProvider.toClient)

    println("accountFrom.amount = ${accountFrom.amount.value}")
    println("accountTo.amount = ${accountTo.amount.value}")

    dboService.transferMoney(DataProvider.fromClient,DataProvider.toClient, Money(500.toBigDecimal(),643))

    println("accountFrom.amount = ${accountFrom.amount.value}")
    println("accountTo.amount = ${accountTo.amount.value}")
    val dboService1 = context.getBean(DboService::class)

}