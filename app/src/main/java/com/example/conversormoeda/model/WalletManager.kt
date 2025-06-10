object WalletManager {

    val currencies: MutableMap<String, Double> = mutableMapOf(
        "BRL" to 100000.0,
        "USD" to 50000.0,
        "BTC" to 0.5,
    )

    var balanceInBRL: Double
        get() = currencies["BRL"] ?: 0.0
        set(value) {
            currencies["BRL"] = value
        }

    fun addBalance(amount: Double) {
        balanceInBRL += amount
    }

    fun updateCurrency(currency: String, amount: Double) {
        currencies[currency] = amount
    }

    fun getCurrencyBalance(currency: String): Double {
        return currencies[currency] ?: 0.0
    }
}
