package com.example.model

import java.math.BigDecimal

data class Money(
    val value: BigDecimal,
    val currency: Int
)
