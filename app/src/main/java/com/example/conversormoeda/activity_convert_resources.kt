package com.example.conversormoeda

import com.example.conversormoeda.model.AwesomeApiService
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class activity_convert_resources : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvBalance: TextView
    private lateinit var lvCurrencyBalances: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_resources)
        val etAmount: EditText = findViewById(R.id.et_amount_to_convert)
        val spFromCurrency: Spinner = findViewById(R.id.sp_currency_from)
        val spToCurrency: Spinner = findViewById(R.id.sp_currency_to)
        val btnConvert: Button = findViewById(R.id.btn_convert)
        val btnBack: Button = findViewById(R.id.btn_back)
        progressBar = findViewById(R.id.progress_bar)
        tvBalance = findViewById(R.id.tv_available_balance)
        lvCurrencyBalances = findViewById(R.id.lv_currency_balances)


        val currencies = WalletManager.currencies.keys.toList()
        spFromCurrency.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        spToCurrency.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)


        updateBalanceDisplay()


        btnConvert.setOnClickListener {
            val amount = etAmount.text.toString().toDoubleOrNull()
            val fromCurrency = spFromCurrency.selectedItem.toString()
            val toCurrency = spToCurrency.selectedItem.toString()

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Valor inválido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (WalletManager.getCurrencyBalance(fromCurrency) < amount) {
                Toast.makeText(this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            convertCurrency(fromCurrency, toCurrency, amount)
        }


        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun updateBalanceDisplay() {
        val currencyBalances = WalletManager.currencies.entries.map {
            val formattedValue = if (it.key == "BTC") {
                "%.4f".format(it.value)
            } else {
                "%.2f".format(it.value)
            }
            "${it.key}: $formattedValue"
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, currencyBalances)
        lvCurrencyBalances.adapter = adapter
    }



    private fun convertCurrency(from: String, to: String, amount: Double) {
        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val rate = fetchExchangeRate(from, to)
                if (rate != null) {
                    val convertedAmount = amount * rate

                    WalletManager.updateCurrency(from, WalletManager.getCurrencyBalance(from) - amount)
                    WalletManager.updateCurrency(to, WalletManager.getCurrencyBalance(to) + convertedAmount)

                    updateBalanceDisplay()
                    Toast.makeText(this@activity_convert_resources, "Conversão concluída!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@activity_convert_resources, "Erro ao obter taxa de conversão!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@activity_convert_resources, "Erro de conexão!", Toast.LENGTH_SHORT).show()
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }

    private suspend fun fetchExchangeRate(from: String, to: String): Double? {
        return withContext(Dispatchers.IO) {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://economia.awesomeapi.com.br/json/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(AwesomeApiService::class.java)
                val pair = "$from-$to"
                val response = service.getExchangeRate(pair).execute()

                if (response.isSuccessful) {
                    val body = response.body()
                    val jsonKey = from + to
                    val rate = body?.get(jsonKey)?.bid?.toDoubleOrNull()
                    rate
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }







}
