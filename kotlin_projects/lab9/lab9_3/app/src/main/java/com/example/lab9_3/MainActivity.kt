package com.example.lab9_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var usersDBHelper : DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var members: ArrayList<Member> = ArrayList()
        members.add(Member("7", "Iam", "02-12-2021","programmer",801232323))
        members.add(Member("8", "Wer", "12-01-2021", "manager",80194332))
        members.add(Member("9", "Iuy", "02-02-2021","director", 801323324))
        members.add(Member("10", "Qwe", "02-10-2021","security", 80121232))
        members.add(Member("11", "Pot", "03-12-2021","professor",9201232))

        usersDBHelper = DBHelper(this)

        for(m in members){
            usersDBHelper.insertUser(m)
        }


        val t1: TextView = this.findViewById(R.id.editTextTextPersonName)
        val t2: TextView = this.findViewById(R.id.editTextTextPersonName2)
        val t3: TextView = this.findViewById(R.id.editTextTextPersonName3)

        val b1:Button = this.findViewById(R.id.button)
        b1.setOnClickListener {
            val act2 = Intent(this, Show::class.java)
            startActivity(act2)
        }
        val b2:Button = this.findViewById(R.id.button2)
        b2.setOnClickListener {
            var s1: String = t1.text.toString()
            var s2: String = t3.text.toString()
            var s3: String = t2.text.toString()
            t1.setText("")
            t2.setText("")
            t3.setText("")
            usersDBHelper.insertUser(Member(s1, s2, s3,"default",0))
            b2.isEnabled = false
        }
        val b3:Button = this.findViewById(R.id.button3)
        b3.setOnClickListener {
            var s1: String = t1.text.toString()
            var s2: String = t3.text.toString()
            var s3: String = t2.text.toString()
            if(usersDBHelper.readUser(s1).size != 0) {
                usersDBHelper.deleteUser(s1)
                usersDBHelper.insertUser(Member(s1,s2,s3,"default",0))
                t1.setText("")
                t2.setText("")
                t3.setText("")
            }

        }

    }
}