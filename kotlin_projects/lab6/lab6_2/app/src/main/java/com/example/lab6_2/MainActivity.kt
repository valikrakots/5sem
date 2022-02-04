package com.example.lab6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import java.lang.Exception
import java.lang.Math.sin

class MainActivity : AppCompatActivity() {
    var selected = 0
    var go = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(com.example.lab6_2.R.id.button2)
        val button1: Button = findViewById(com.example.lab6_2.R.id.button3)
        val button2: Button = findViewById(com.example.lab6_2.R.id.button4)
        val buttonGo: Button = findViewById(com.example.lab6_2.R.id.button5)
        val textView: TextView = findViewById(com.example.lab6_2.R.id.textView)
        val textView2: TextView = findViewById(com.example.lab6_2.R.id.textView3)
        val textView3: TextView = findViewById(com.example.lab6_2.R.id.textView4)
        val result: TextView = findViewById(com.example.lab6_2.R.id.textView5)
        val input: EditText = findViewById(com.example.lab6_2.R.id.editTextNumberSigned4)
        val input1: EditText = findViewById(com.example.lab6_2.R.id.editTextNumberSigned5)
        val input2: EditText = findViewById(com.example.lab6_2.R.id.editTextNumberSigned3)
        result.text = ""
        textView.text = getString(R.string.text1)
        textView2.text = getString(R.string.text2)
        textView3.text = getString(R.string.text3)

        button.setOnClickListener {
            textView2.isVisible = true
            textView3.isVisible = true
            input1.isVisible = true
            input2.isVisible = true
            selected = 0
            textView.text = getString(R.string.text1)
            textView2.text = getString(R.string.text2)
            textView3.text = getString(R.string.text3)
        }

        button1.setOnClickListener {
            textView2.isVisible = true
            textView3.isVisible = true
            input1.isVisible = true
            input2.isVisible = true
            selected = 1
            textView.text = getString(R.string.text1)
            textView2.text = getString(R.string.text3)
            textView3.text = getString(R.string.text4)
        }

        button2.setOnClickListener {
            selected = 2
            input1.isVisible = false
            input2.isVisible = false
            textView.text = getString(R.string.text1)
            textView2.isVisible = false
            textView3.isVisible = false
        }

        buttonGo.setOnClickListener {
            if(go == 1){
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
}