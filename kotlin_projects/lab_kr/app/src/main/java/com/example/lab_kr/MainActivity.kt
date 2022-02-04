package com.example.lab_kr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val one: EditText = this.findViewById(R.id.editTextTextPersonName)
        val two: EditText = this.findViewById(R.id.editTextTextPersonName2)
        val three: EditText = this.findViewById(R.id.editTextPhone)
        val btn: Button = this.findViewById(R.id.button)

        btn.setOnClickListener {
            if (one.text.toString() != "" && two.text.toString() != "" && three.text.toString() != ""){
                val intent = Intent(this@MainActivity,MainActivity2::class.java)
                intent.putExtra("Username",one.text.toString())
                intent.putExtra("Surname",two.text.toString())
                intent.putExtra("Phone",three.text.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "All fields must be set", Toast.LENGTH_SHORT).show()
            }
        }
    }
}