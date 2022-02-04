package com.example.lab9_4

class Info {
    var id: String = ""
    var zavods: Int = 0
    var shops:  Int = 0


    constructor(){}

    constructor(id:String, zavods: Int, shops: Int) {
        this.id = id
        this.shops = shops
        this.zavods = zavods
    }

}