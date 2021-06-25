package com.example.dummyapi

class Market(
    var exchange_id : String = "",
    var symbol : String= "",
    var price_unconverted : Double = 0.0,
    var price : Double= 0.0,
    var change_24h : Double= 0.0,
    var spread : Double= 0.0,
    var volume_24h : Double= 0.0,
    var status : String= "",
    var time : String= ""
) {
}