package com.example.lab_kr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val profileName=intent.getStringExtra("Username")
        val profileSurname=intent.getStringExtra("Surname")
        val phone=intent.getStringExtra("Phone")

        val one: TextView = this.findViewById(R.id.textView2)
        val two: TextView = this.findViewById(R.id.textView3)
        val btn: Button = this.findViewById(R.id.button2)

        one.text = profileName + " " + profileSurname
        two.text = phone

        btn.setOnClickListener {
            val intent = Intent(this@MainActivity2,MainActivity3::class.java)
            startActivity(intent)
        }

    }
}