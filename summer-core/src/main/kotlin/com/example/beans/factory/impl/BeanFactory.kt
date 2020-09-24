package com.example.beans.factory.impl

import kotlin.reflect.KClass

interface BeanFactory {
    fun <T : Any> createBean(type: KClass<T>): T
}
