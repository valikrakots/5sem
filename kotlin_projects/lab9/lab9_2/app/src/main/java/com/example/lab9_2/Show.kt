package com.example.lab9_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView




class Show : AppCompatActivity() {
    lateinit var usersDBHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val table: TableLayout = this.findViewById(R.id.layout)



        usersDBHelper = DBHelper(this)
        var members: ArrayList<Member> = usersDBHelper.readAllUsers()

        for (i in 0 until members.size) {
            val row = TableRow(this)
            for (j in 0 until 3) {
                var value: String = ""
                if(j == 0)
                    value = members[i].id
                else if(j == 1)
                    value = members[i].name
                else if(j == 2)
                    value = members[i].date
                val tv = TextView(this)
                tv.text = value
                tv.setPadding(10, 20, 10, 20)
                row.addView(tv)
            }
            table.addView(row)
        }
    }
}