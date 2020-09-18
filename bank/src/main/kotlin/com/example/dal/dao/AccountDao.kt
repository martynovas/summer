package com.example.dal.dao

import com.example.model.Account
import com.example.model.Client

interface AccountDao {
    fun findAccounts(client: Client): List<Account>

    fun findDefaultAccount(client: Client): Account?
}
