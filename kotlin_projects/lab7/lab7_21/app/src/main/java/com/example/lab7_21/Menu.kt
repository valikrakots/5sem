package com.example.lab7_21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.lab7_21.R

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val button: Button = findViewById(com.example.lab7_21.R.id.button)
        button.setOnClickListener {
            val act2 = Intent(this, MainActivity::class.java)
            startActivity(act2)
        }
    }
}