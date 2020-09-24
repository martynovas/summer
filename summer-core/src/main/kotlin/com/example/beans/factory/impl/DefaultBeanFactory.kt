package com.example.beans.factory.impl

import com.example.beans.aop.ProxyConfigurator
import com.example.beans.config.Config
import com.example.beans.factory.annotation.PostConstruct
import org.reflections.Reflections
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation

class DefaultBeanFactory(
    private val config: Config,
    private val scanner: Reflections,

    private val proxyConfigurators: MutableList<ProxyConfigurator> = mutableListOf(),
) : BeanFactory {

    init {
        for (clazz in scanner.getSubTypesOf(ProxyConfigurator::class.java)) {
            proxyConfigurators.add(clazz.getDeclaredConstructor().newInstance())
        }
    }

    override fun <T : Any> createBean(type: KClass<T>): T =
        resolveImpl(type)
            .let { it to it.createInstance() }
            .let { (type, instance) ->
                invokeInitMethods(type, instance)
                wrapWithProxyIfNeeded(type, instance)
            }

    private fun <T : Any> resolveImpl(type: KClass<T>) =
        if (type.java.isInterface) {
            config.findImplClass(type)
                ?: resolveByType(type)
        } else type

    private fun <T : Any> resolveByType(type: KClass<T>): KClass<T> {
        val classes = scanner.getSubTypesOf(type.java)
        if (classes.size != 1) {
            throw IllegalStateException("0 or more than one impl found for type $type please update your config.")
        }
        return classes.iterator().next().kotlin as KClass<T>
    }



    private fun <T : Any> invokeInitMethods(type: KClass<T>, arg: T) {
        for (method in type.declaredFunctions) {
            method.findAnnotation<PostConstruct>()
                ?.let { method.call(arg) }
        }
    }

    private fun <T : Any> wrapWithProxyIfNeeded(type: KClass<T>, obj: T): T {
        var result: T? = null

        proxyConfigurators.forEach {
            result = it.wrapWithPoxy(obj, type) as T?
        }

        return result ?: obj
    }
}