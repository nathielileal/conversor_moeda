package com.example.conversormoeda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvBalance: TextView
    private lateinit var tvAssetBRL: TextView
    private lateinit var tvAssetUSD: TextView
    private lateinit var tvAssetBTC: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvBalance = findViewById(R.id.tv)
        tvAssetBRL = findViewById(R.id.tv_asset_brl)
        tvAssetUSD = findViewById(R.id.tv_asset_usd)
        tvAssetBTC = findViewById(R.id.tv_asset_btc)

        val btnConvertResources: Button = findViewById(R.id.btn_convert_resources)



        btnConvertResources.setOnClickListener {
            startActivity(Intent(this, activity_convert_resources::class.java))
        }

        updateUI()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        tvBalance.text = "Saldo: R$ ${"%.2f".format(Locale.US, WalletManager.balanceInBRL)}"

        tvAssetBRL.text = "BRL: R$ ${"%.2f".format(Locale.US, WalletManager.balanceInBRL)}"
        tvAssetUSD.text = "USD: $${"%.2f".format(Locale.US, WalletManager.getCurrencyBalance("USD"))}"
        tvAssetBTC.text = "BTC: ${"%.4f".format(Locale.US, WalletManager.getCurrencyBalance("BTC"))}"
    }
}
