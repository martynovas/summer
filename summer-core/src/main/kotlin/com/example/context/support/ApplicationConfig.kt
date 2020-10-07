package com.example.context.support

import kotlin.reflect.KClass

interface ApplicationConfig {
    fun <T:Any> findImpl(inrf:KClass<in T>): KClass<T>
}