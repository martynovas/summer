package com.example.dal.dao.impl

import com.example.beans.factory.annotation.Component
import com.example.dal.dao.AccountDao
import com.example.dal.provider.DataProvider
import com.example.model.Account
import com.example.model.Client

@Component
class AccountDaoImpl : AccountDao {
    override fun findAccounts(client: Client): List<Account> =
        DataProvider.accounts
            .filter {
                it.client.id == client.id
            }

    override fun findDefaultAccount(client: Client): Account? =
        DataProvider.accounts.firstOrNull {
            it.client.id == client.id && it.isDefault
        }

}
