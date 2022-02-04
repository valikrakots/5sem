package com.example.lab8_3

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import java.lang.Exception
import java.lang.Math.sin

class MainActivity : AppCompatActivity(),
    GestureOverlayView.OnGesturePerformedListener{
    var selected = 0
    var go = 1
    var f = 0
    private var gLibrary: GestureLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gestureSetup()

        val button: Button = findViewById(com.example.lab8_3.R.id.button2)
        val button1: Button = findViewById(com.example.lab8_3.R.id.button3)
        val button2: Button = findViewById(com.example.lab8_3.R.id.button4)
        val buttonGo: Button = findViewById(com.example.lab8_3.R.id.button5)
        val textView: TextView = findViewById(com.example.lab8_3.R.id.textView)
        val textView2: TextView = findViewById(com.example.lab8_3.R.id.textView3)
        val textView3: TextView = findViewById(com.example.lab8_3.R.id.textView4)
        val result: TextView = findViewById(com.example.lab8_3.R.id.textView5)
        val input: EditText = findViewById(com.example.lab8_3.R.id.editTextNumberSigned4)
        val input1: EditText = findViewById(com.example.lab8_3.R.id.editTextNumberSigned5)
        val input2: EditText = findViewById(com.example.lab8_3.R.id.editTextNumberSigned3)
        result.text = ""
        textView.text = getString(R.string.text1)
        textView2.text = getString(R.string.text2)
        textView3.text = getString(R.string.text3)

        button.setOnClickListener {
            f = 0
            textView2.isVisible = true
            textView3.isVisible = true
            input1.isVisible = true
            input2.isVisible = true
            selected = 0
            textView.text = getString(R.string.text1)
            textView2.text = getString(R.string.text2)
            textView3.text = getString(R.string.text3)
            input.text.clear()
            input1.text.clear()
            input2.text.clear()
        }

        button1.setOnClickListener {
            f = 0
            textView2.isVisible = true
            textView3.isVisible = true
            input1.isVisible = true
            input2.isVisible = true
            selected = 1
            textView.text = getString(R.string.text1)
            textView2.text = getString(R.string.text3)
            textView3.text = getString(R.string.text4)
            input.text.clear()
            input1.text.clear()
            input2.text.clear()
        }

        button2.setOnClickListener {
            f = 0
            selected = 2
            input1.isVisible = false
            input2.isVisible = false
            textView.text = getString(R.string.text1)
            textView2.isVisible = false
            textView3.isVisible = false
            input.text.clear()
            input1.text.clear()
            input2.text.clear()
        }

        buttonGo.setOnClickListener {
            if(go == 1){
                f = 0
                go = 0
                buttonGo.text = "Retry"
                try {

                    if (selected == 0) {
                        val a = input.text.toString().toDouble()
                        val b = input1.text.toString().toDouble()
                        val c = input2.text.toString().toDouble()
                        var sin = Math.sin(c.toDouble()/180.0*Math.PI)
                        var res = 0.5 * a * b * sin
                        var res1 = res.toString()
                        result.text = res1
                    }
                    else if (selected == 1) {
                        val a = input.text.toString().toInt()
                        val b = input1.text.toString().toInt()
                        val c = input2.text.toString().toInt()
                        var res = 0.5*a*a*Math.sin(b.toDouble()/180.0*Math.PI)*Math.sin(b.toDouble()/180.0*Math.PI)/Math.sin((180 - b.toDouble() - c.toDouble())/180*Math.PI)
                        var res1 = res.toString()
                        result.text = res1
                    } else {
                        val a = input.text.toString().toInt()
                        var res = a*a*Math.sqrt(3.0)/4.0
                        var res1 = res.toString()
                        result.text = res1
                    }
                }
                catch (e:Exception){
                    result.text = "Wrong format"
                }
                input.isEnabled = false
                input1.isEnabled = false
                input2.isEnabled = false
                button.isEnabled = false
                button1.isEnabled = false
                button2.isEnabled = false
            }
            else{
                go = 1
                result.text = ""
                buttonGo.text = getString(R.string.button4)
                input.text.clear()
                input1.text.clear()
                input2.text.clear()
                input.isEnabled = true
                input1.isEnabled = true
                input2.isEnabled = true
                button.isEnabled = true
                button1.isEnabled = true
                button2.isEnabled = true
            }
        }



    }


    private fun gestureSetup(){
        gLibrary = GestureLibraries.fromRawResource(this, com.example.lab8_3.R.raw.gestures)

        if(gLibrary?.load() == false){
            finish()
        }
        val i: GestureOverlayView = this.findViewById(com.example.lab8_3.R.id.ingest)
        i.addOnGesturePerformedListener(this)
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val prediction = gLibrary?.recognize(gesture)

        prediction?.let{
            if(it.size > 0 && it[0].score > 1.0){
                val action = it[0].name
                val text:TextView
                if(f%3 == 0)
                    text = findViewById(com.example.lab8_3.R.id.editTextNumberSigned4)
                else if(f%3 == 1)
                    text = findViewById(com.example.lab8_3.R.id.editTextNumberSigned5)
                else
                    text = findViewById(com.example.lab8_3.R.id.editTextNumberSigned3)
                if (action == "one")
                    text.text = text.text.toString() + "1"
                else if (action == "zero") {
                    if (text.text.toString() != "")
                        text.text = text.text.toString() +"0"
                }
                else if (action == "two")
                    text.text = text.text.toString() + "2"
                else if (action == "three")
                    text.text = text.text.toString() + "3"
                else if (action == "four")
                    text.text = text.text.toString() +"4"
                else if (action == "five")
                    text.text = text.text.toString() + "5"
                else if (action == "six")
                    text.text = text.text.toString() +"6"
                else if (action == "seven")
                    text.text = text.text.toString() +"7"
                else if (action == "eight")
                    text.text = text.text.toString() +"8"
                else if (action == "nine")
                    text.text = text.text.toString() +"9"
                else if (action == "divide") {
                    f += 1
                }
            }
        }
    }
}