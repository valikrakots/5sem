package com.example.lab9_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.lab9_1.R

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val button: Button = findViewById(com.example.lab9_1.R.id.button1)
        button.setOnClickListener {
            val act2 = Intent(this, MainActivity::class.java)
            startActivity(act2)
        }
        val button1: Button = findViewById(com.example.lab9_1.R.id.button)
        button1.setOnClickListener {
            val act2 = Intent(this, Info::class.java)
            startActivity(act2)
        }
    }
}