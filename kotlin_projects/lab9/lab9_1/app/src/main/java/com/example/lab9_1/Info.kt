package com.example.lab9_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData

class Info : AppCompatActivity() {

    var one = ""
    var two = ""
    var three = ""
    var four = ""
    var five = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.lab9_1.R.layout.activity_info)


        val t1:TextView = findViewById(com.example.lab9_1.R.id.textView7)
        val t2:TextView = findViewById(com.example.lab9_1.R.id.textView8)
        val t3:TextView = findViewById(com.example.lab9_1.R.id.textView9)
        val t4:TextView = findViewById(com.example.lab9_1.R.id.textView10)
        val t5:TextView = findViewById(com.example.lab9_1.R.id.textView11)


        val sharedPref = this.getSharedPreferences("mine",MODE_PRIVATE)
        val loginSharedPref = this.getSharedPreferences("login",MODE_PRIVATE)
        one = sharedPref.getInt("attemps",0).toString()
        two = sharedPref.getInt("launches",0).toString()
        three = loginSharedPref.getString("email","").toString()
        four = loginSharedPref.getString("pass","").toString()
        five= sharedPref.getString("id","").toString()

        t1.text = one
        t2.text = two
        t3.text = three
        t4.text = four
        t5.text = five


    }

}