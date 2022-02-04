package com.example.lab9_4

import java.text.SimpleDateFormat
import java.util.*

class Rajon {
    var id: String = ""
    var name: String = ""
    var population:  Int = 0
    constructor(){}

    constructor(id:String, name:String, population: Int) {
        this.id = id
        this.name = name
        this.population = population
    }

}