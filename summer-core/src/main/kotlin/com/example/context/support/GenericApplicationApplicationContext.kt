package com.example.context.support

import com.example.beans.config.Config
import com.example.beans.factory.impl.BeanFactory
import com.example.beans.factory.impl.DefaultBeanFactory
import com.example.beans.prepare.PrepareProcessors
import org.reflections.Reflections
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.reflect.KClass

class GenericApplicationApplicationContext(config: Config) : ApplicationContextAware {
    private val beans: ConcurrentMap<String, Any> = ConcurrentHashMap()
    private val scanner: Reflections = Reflections(config.packagesToScan())
    private val beanFactory: BeanFactory = createBeanFactory(config)

    init {
        processPrepareContext()
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getBean(clazz: KClass<T>): T =
        findBean(clazz)
            ?: beanFactory.createBean(clazz)



    private fun createBeanFactory(
        config: Config,
    ): BeanFactory =
        DefaultBeanFactory(
            config,
            scanner
        )

    private fun <T : Any> findBean(type: KClass<T>): T? =
        beans[type.java.canonicalName] as T?


    private fun processPrepareContext() {
        for (clazz in scanner.getSubTypesOf(PrepareProcessors::class.java)) {
            clazz.getDeclaredConstructor()
                .newInstance()
                .configure( scanner, this)
        }
    }

    override fun <T : Any> addBean(beanName: String, bean: T) {
        beans[beanName] = bean
    }

}
