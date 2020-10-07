package com.example.service.impl

import com.example.beans.factory.annotation.Autowired
import com.example.beans.factory.annotation.Component
import com.example.model.Account
import com.example.model.Money
import com.example.service.BankService
import java.lang.Exception

@Component
class BankServiceImpl @Autowired constructor(
) : BankService {
    override fun transferMoney(fromAccount: Account, toAccount: Account, amount: Money) {
        validateTransfer(fromAccount, amount)

        fromAccount.amount -= amount
        toAccount += amount
    }

    private fun validateTransfer(fromAccount: Account, amount: Money){
        if (fromAccount.amount.value<amount.value)
            throw Exception("Not enough money")
    }
}
