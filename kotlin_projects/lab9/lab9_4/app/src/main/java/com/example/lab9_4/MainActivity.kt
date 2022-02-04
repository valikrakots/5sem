package com.example.lab9_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var usersDBHelper : DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rajons: ArrayList<Rajon> = ArrayList()
        rajons.add(Rajon("1", "Perv", 801232323))
        rajons.add(Rajon("2", "Frunz",80194332))
        rajons.add(Rajon("3", "Centr", 801323324))
        rajons.add(Rajon("4", "Sovet", 80121232))

        usersDBHelper = DBHelper(this)

        for(m in rajons){
            usersDBHelper.insertUser1(m)
        }

        var infos: ArrayList<Info> = ArrayList()
        infos.add(Info("1", 98, 23))
        infos.add(Info("2", 32,332))
        infos.add(Info("3", 54, 324))
        infos.add(Info("4", 34, 32))


        for(m in infos){
            usersDBHelper.insertUser2(m)
        }

        val t1: TextView = this.findViewById(R.id.editTextTextPersonName)
        val t2: TextView = this.findViewById(R.id.editTextTextPersonName2)

        val b1:Button = this.findViewById(R.id.button)
        b1.setOnClickListener {
            val act2 = Intent(this, Show::class.java)
            startActivity(act2)
            t2.text = ""
        }
        val b2:Button = this.findViewById(R.id.button2)
        b2.setOnClickListener {
            var i = usersDBHelper.sumShops()
            t1.text = i.toString()
            t2.text = ""
        }
        val b3:Button = this.findViewById(R.id.button3)
        b3.setOnClickListener {
            if(t2.text.toString() == ""){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("You have to write zavod name first")
                builder.show()
            }
            else {
                var i = usersDBHelper.amountZavods(t2.text.toString())
                t1.text = i.toString()
            }
        }

    }
}