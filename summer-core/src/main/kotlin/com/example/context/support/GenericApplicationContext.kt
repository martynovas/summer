package com.example.context.support

import kotlin.reflect.KClass

class GenericApplicationContext : ApplicationContext {
    override  fun <T : Any> getBean(clazz: KClass<T>): T {
        TODO("Not yet implemented")
    }
}
