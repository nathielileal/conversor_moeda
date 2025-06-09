package com.example.conversordemoedas

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.conversordemoedas.model.WalletManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

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
        val tvBalance: TextView = findViewById(R.id.tv_balance)
        tvBalance.text = "Saldo: R$ ${WalletManager.balanceInBRL}"
    }
}

