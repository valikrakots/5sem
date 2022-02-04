package com.example.lab6


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var played = false;
    var atemp = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.lab6.R.layout.activity_main)

        val button: Button = findViewById(com.example.lab6.R.id.button2)
        val input: EditText = findViewById(com.example.lab6.R.id.editTextNumber)
        val text:TextView = findViewById(com.example.lab6.R.id.textView5)
        val prog:ProgressBar = findViewById(com.example.lab6.R.id.progressBar2)
        val alertDialogBuilder = AlertDialog.Builder(this)
        button.setOnClickListener {
            if(!played){
                try {
                    var cz3 =  input.text.toString()
                    var cz1 = 0
                    if (cz1 < 0 || cz1 > 10){
                        throw Exception("Wrong values")
                    }
                    played = true
                    //val animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
                    val animation2 = AnimationUtils.loadAnimation(this, com.example.lab6.R.anim.zoom)
                    //input.startAnimation(animation)
                    button.startAnimation(animation2)
                    var cz = Random.nextInt(0,10)
                    if (cz == cz1){
                        //text.text = "Correct";
                        alertDialogBuilder.setTitle("Result")
                        alertDialogBuilder.setMessage("Correct")
                        alertDialogBuilder.show()
                    }
                    else{
                        //text.text = "Wrong. Number is " + cz.toString()
                        alertDialogBuilder.setTitle("Result")
                        alertDialogBuilder.setMessage("Wrong. Number is " + cz.toString())
                        alertDialogBuilder.show()
                    }
                    text.text = "Press Retry"
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
                //val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                val animation2 = AnimationUtils.loadAnimation(this, com.example.lab6.R.anim.zoomout)
                //input.startAnimation(animation)
                button.startAnimation(animation2)
                text.text = getString(com.example.lab6.R.string.text1)
                input.text.clear()
                input.isEnabled = true
                button.text = getString(com.example.lab6.R.string.button1)
                played = false
            }
        }

    }


}


