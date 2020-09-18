package com.example.service

import com.example.model.Client
import com.example.model.Money

interface DboService {
    fun transferMoney(fromClient: Client, toClient: Client, amount: Money)
}
