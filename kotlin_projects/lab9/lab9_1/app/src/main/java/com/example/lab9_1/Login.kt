package com.example.lab9_1


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.asLiveData
import com.example.lab9_1.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import android.content.SharedPreferences
import android.provider.Settings
import java.security.AccessController.getContext


class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val sharedPref = this.getSharedPreferences("mine",MODE_PRIVATE)
        val loginSharedPref = this.getSharedPreferences("login",MODE_PRIVATE)
        val auth = sharedPref.getBoolean("auth",false)
        var launches = sharedPref.getInt("launches",0)
        launches += 1
        with (sharedPref.edit()) {
            putInt("launches", launches)
            commit()
        }

        if (auth){
            val act2 = Intent(this, Menu::class.java)
            startActivity(act2)
        }

        val button: Button = findViewById(R.id.buttonLogin)
        val em: EditText = findViewById(R.id.editTextTextEmailAddress)
        val pas: EditText = findViewById(R.id.editTextTextPassword)
        var id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        button.setOnClickListener {

            with (sharedPref.edit()) {
                putBoolean("auth", true)
                commit()
            }
            with(loginSharedPref.edit()){
                putString("email", em.text.toString())
                commit()
            }
            with(loginSharedPref.edit()){
                putString("pass",pas.text.toString())
                commit()
            }
            with(sharedPref.edit()){
                putString("id",id)
                commit()
            }
            val act2 = Intent(this, Menu::class.java)
            startActivity(act2)
        }
    }
}