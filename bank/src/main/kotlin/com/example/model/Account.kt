package com.example.model

data class Account(
    val amount: Money,
    val client: Client,
    val isDefault: Boolean
)
