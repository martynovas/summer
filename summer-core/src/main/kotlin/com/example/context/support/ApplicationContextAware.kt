package com.example.context.support

interface ApplicationContextAware : ApplicationContext{
    fun <T : Any> addBean(beanName: String, bean: T)
}
