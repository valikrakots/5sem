package com.example.lab9_2

import java.text.SimpleDateFormat
import java.util.*

class Member {
    var id: String = ""
    var name: String = ""
    var date:  String = ""

    constructor(){}

    constructor(id:String, name:String, date:String) {
        this.id = id
        this.name = name
        this.date = date
    }

}