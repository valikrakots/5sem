package com.example.lab9_3

import java.text.SimpleDateFormat
import java.util.*

class Member {
    var id: String = ""
    var name: String = ""
    var date:  String = ""
    var dolz: String = ""
    var phone: Int = 0

    constructor(){}

    constructor(id:String, name:String, date:String, dolz: String, phone: Int) {
        this.id = id
        this.name = name
        this.date = date
        this.phone = phone
        this.dolz = dolz
    }

}