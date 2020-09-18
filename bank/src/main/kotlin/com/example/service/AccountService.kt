package com.example.service

import com.example.model.Account
import com.example.model.Client

interface AccountService {
    fun findAccounts(client: Client): List<Account>

    fun getDefaultAccount(client: Client): Account
}
