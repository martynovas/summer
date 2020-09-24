package com.example.beans.factory.impl

import com.example.beans.config.Config
import org.reflections.Reflections
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class DefaultBeanFactory(
    private val config: Config,
    private val scanner: Reflections,
) : BeanFactory {

    override fun <T : Any> createBean(type: KClass<T>): T =
        type.createInstance()

}
