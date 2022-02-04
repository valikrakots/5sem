package com.example.lab8_2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity(),
    GestureOverlayView.OnGesturePerformedListener {

    var played = false;
    private var gLibrary: GestureLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.lab8_2.R.layout.activity_main)
        gestureSetup()

        val button: Button = findViewById(com.example.lab8_2.R.id.button2)
        val text:TextView = findViewById(com.example.lab8_2.R.id.textView5)
        button.setOnClickListener {
            if(!played){
                try {
                    var cz1 =  text.text.toString().toInt()
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
                    button.text = "Retry"
                }
                catch (e: Exception){
                    text.textSize = 16F
                    text.text = "It should be int from 0 to 10"
                }

            }
            else{
                text.text = getString(com.example.lab8_2.R.string.text1)
                button.text = getString(com.example.lab8_2.R.string.button1)
                played = false
            }
        }

    }

    private fun gestureSetup(){
        gLibrary = GestureLibraries.fromRawResource(this, com.example.lab8_2.R.raw.gestures)

        if(gLibrary?.load() == false){
            finish()
        }
        val i: GestureOverlayView = this.findViewById(com.example.lab8_2.R.id.ingest)
        i.addOnGesturePerformedListener(this)
    }


    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        if (played)
            return
        val prediction = gLibrary?.recognize(gesture)

        prediction?.let{
            if(it.size > 0 && it[0].score > 1.0){
                val action = it[0].name
                val text:TextView = findViewById(com.example.lab8_2.R.id.textView5)
                if (action == "One")
                    text.text = "1"
                else if (action == "Two")
                    text.text = "2"
                else if (action == "Three")
                    text.text = "3"
                else if (action == "Four")
                    text.text = "4"
                else if (action == "Five")
                    text.text = "5"
                else if (action == "Six")
                    text.text = "6"
                else if (action == "Seven")
                    text.text = "7"
                else if (action == "Eight")
                    text.text = "8"
                else if (action == "Nine")
                    text.text = "9"
            }
        }
    }


}


