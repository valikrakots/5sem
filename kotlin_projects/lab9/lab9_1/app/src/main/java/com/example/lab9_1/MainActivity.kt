package com.example.lab9_1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var played = false
    var atemp = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.lab9_1.R.layout.activity_main)

        val sharedPref = this.getSharedPreferences("mine",MODE_PRIVATE)
        atemp = sharedPref.getInt("attemps",0)

        val button: Button = findViewById(com.example.lab9_1.R.id.button2)
        val input: EditText = findViewById(com.example.lab9_1.R.id.editTextNumber)
        val text:TextView = findViewById(com.example.lab9_1.R.id.textView5)
        val prog: ProgressBar = findViewById(com.example.lab9_1.R.id.progressBar2)
        button.setOnClickListener {
            if(!played){
                try {
                    var cz1 =  input.text.toString().toInt()
                    if (cz1 < 0 || cz1 > 10){
                        throw Exception("Wrong values")
                    }
                    played = true
                    var cz = Random.nextInt(0,10)
                    if (cz == cz1){
                        text.text = "Correct";
                    }
                    else{
                        text.text = "Wrong. Number is " + cz.toString()
                    }
                    atemp += 1
                    prog.progress = atemp%10
                    input.isEnabled = false
                    button.text = "Retry"
                }
                catch (e: Exception){
                    text.textSize = 16F
                    input.text.clear()
                    input.clearFocus()
                    text.text = "It should be int from 0 to 10"
                }

            }
            else{
                text.text = getString(com.example.lab9_1.R.string.text1)
                input.text.clear()
                input.isEnabled = true
                button.text = getString(com.example.lab9_1.R.string.button1)
                played = false
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val sharedPref = this.getSharedPreferences("mine",MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("attemps", atemp)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref = this.getSharedPreferences("mine",MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("attemps", atemp)
            commit()
        }
    }


}


