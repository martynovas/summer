package com.example.service

import com.example.model.Account
import com.example.model.Money

interface BankService {
    fun transferMoney(fromAccount: Account, toAccount: Account, amount: Money)
}
