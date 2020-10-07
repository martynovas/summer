package com.example.context.support

import com.example.beans.factory.annotation.Autowired
import java.lang.Exception
import kotlin.reflect.KClass
import kotlin.reflect.full.hasAnnotation

class GenericApplicationContext(val config:ApplicationConfig) : ApplicationContext {
    val objects: MutableMap<KClass<Any>, Any> = mutableMapOf()

    private  fun <T : Any> getNewBean(clazz: KClass<T>): T {
        var impl = config.findImpl(clazz as KClass<Any>)

        var cons = impl.constructors.findLast { it.hasAnnotation<Autowired>()}

        if (cons == null && impl.constructors.size==1)
            cons = impl.constructors.first()

        if (cons == null)
            throw Exception("Constructor not found")

        val param = cons.parameters.map { getBean(it.type.classifier as KClass<Any>) }
        var obj= cons.call(*param.toTypedArray())
        objects[clazz] = obj
        return obj as T
    }

    override  fun <T : Any> getBean(clazz: KClass<T>): T = (objects[clazz] ?: getNewBean(clazz)) as T

}
