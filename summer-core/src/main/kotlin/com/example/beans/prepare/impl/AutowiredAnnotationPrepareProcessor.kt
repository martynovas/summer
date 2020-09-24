package com.example.beans.prepare.impl

import com.example.beans.factory.annotation.Autowired
import com.example.beans.factory.annotation.Component
import com.example.beans.prepare.PrepareProcessors
import com.example.context.support.ApplicationContextAware
import org.reflections.Reflections

class AutowiredAnnotationPrepareProcessor : PrepareProcessors {
    override fun configure(scanner: Reflections, ctx: ApplicationContextAware) {
        scanner.getTypesAnnotatedWith(Component::class.java)
            .forEach { clazz ->
                val beanName = clazz.canonicalName
                clazz.constructors.filter {
                    it.isAnnotationPresent(Autowired::class.java) && it.parameters.isNotEmpty()
                }.map {
                    val constructorArgs = it.parameters.map {
                        ctx.getBean(it.type.kotlin)
                    }
                    ctx.addBean(beanName, it.newInstance(*constructorArgs.toTypedArray()))
                }
            }
    }
}
