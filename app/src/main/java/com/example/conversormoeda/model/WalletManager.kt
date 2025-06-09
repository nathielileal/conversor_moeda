package com.example.conversormoeda.model

object WalletManager {
    public val currencies: MutableMap<String, Double> = mutableMapOf(
        "USD" to 0.0,
        "BTC" to 0.0,
        "BRL" to 0.0
    )

    var balanceInBRL: Double
        get() = currencies["BRL"] ?: 0.0
        set(value) {
            currencies["BRL"] = value
        }

    fun addBalance(amount: Double) {
        val newBalance = balanceInBRL + amount
        balanceInBRL = newBalance
    }

    fun updateCurrency(currency: String, amount: Double) {
        currencies[currency] = amount
    }

    fun getCurrencyBalance(currency: String): Double {
        return currencies[currency] ?: 0.0
    }
}
