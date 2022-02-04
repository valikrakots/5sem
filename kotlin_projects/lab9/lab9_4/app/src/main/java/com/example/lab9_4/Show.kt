package com.example.lab9_4

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
        var rajons: ArrayList<Rajon> = usersDBHelper.readAllUsers1()

        for (i in 0 until rajons.size) {
            val row = TableRow(this)
            for (j in 0 until 3) {
                var value: String = ""
                if(j == 0)
                    value = rajons[i].id
                else if(j == 1)
                    value = rajons[i].name
                else if(j == 2)
                    value = rajons[i].population.toString()
                val tv = TextView(this)
                tv.text = value
                tv.setPadding(10, 20, 10, 20)
                row.addView(tv)
            }
            table.addView(row)
        }
        val tv = TextView(this)
        tv.text = ",,,,,,,,,,,,,,,,,,,,,"
        val row = TableRow(this)
        row.addView(tv)
        table.addView(row)

        var infos: ArrayList<Info> = usersDBHelper.readAllUsers2()
        for (i in 0 until infos.size) {
            val row = TableRow(this)
            for (j in 0 until 3) {
                var value: String = ""
                if(j == 0)
                    value = infos[i].id
                else if(j == 1)
                    value = infos[i].zavods.toString()
                else if(j == 2)
                    value = infos[i].shops.toString()
                val tv = TextView(this)
                tv.text = value
                tv.setPadding(10, 20, 10, 20)
                row.addView(tv)
            }
            table.addView(row)
        }
    }
}