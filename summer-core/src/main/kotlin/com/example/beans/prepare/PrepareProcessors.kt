package com.example.beans.prepare

import com.example.context.support.ApplicationContextAware
import org.reflections.Reflections

interface PrepareProcessors {
    fun configure(scanner: Reflections, ctx: ApplicationContextAware)
}
