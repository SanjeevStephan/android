package com.stephan.androidtrivia.xmlpull_parser

class Employee {

    var id: Int = 0
    var name: String = ""
    var salary: Float = 0.toFloat()

    override fun toString(): String {
        var msg = "ID = $id \n"
        msg += "Name = $name \n"
        msg += "Salary = $salary \n"
        return msg
    }
}


//data class emp(val id: Int=0,val name: String = "",val salary: Float = 0.0F)