package com.example.context.support

import kotlin.reflect.KClass

interface ApplicationContext {
     fun <T : Any> getBean(clazz: KClass<T>): T
}
