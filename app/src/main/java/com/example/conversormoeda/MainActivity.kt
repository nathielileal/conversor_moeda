package com.example.conversormoeda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.conversormoeda.model.WalletManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvBalance: TextView = findViewById(R.id.tv)
        val btnDeposit: Button = findViewById(R.id.btn_deposit)
        val btnListResources: Button = findViewById(R.id.btn_list_resources)
        val btnConvertResources: Button = findViewById(R.id.btn_convert_resources)

        tvBalance.text = "Saldo: R$ ${WalletManager.balanceInBRL}"

        btnListResources.setOnClickListener {
            startActivity(Intent(this, activity_list_resources::class.java))
        }

        btnConvertResources.setOnClickListener {
            startActivity(Intent(this, activity_convert_resources::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val tvBalance: TextView = findViewById(R.id.tv)  // Corrigido para o mesmo id do onCreate
        tvBalance.text = "Saldo: R$ ${WalletManager.balanceInBRL}"
    }
}
