package com.example.service

import com.example.context.support.ApplicationConfig
import com.example.dal.dao.AccountDao
import com.example.dal.dao.impl.AccountDaoImpl
import com.example.service.impl.AccountServiceImpl
import com.example.service.impl.BankServiceImpl
import com.example.service.impl.DboServiceImpl
import kotlin.reflect.KClass

class BankConfig:ApplicationConfig {
    val interfaceToImpl = mapOf(AccountService::class to AccountServiceImpl::class,
                                BankService::class to BankServiceImpl::class,
                                DboService::class to DboServiceImpl::class,
                                AccountDao::class to AccountDaoImpl::class)

    override fun <T: Any> findImpl(inrf:KClass<in T>): KClass<T> {
        val v = interfaceToImpl[inrf]!!
        return v as KClass<T>
    }
}