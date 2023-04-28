package com.sanjeev.stephan.murmu.kotlinrecyclerviewapp

data class MyDataModel(val title: String,val desc : String,val image: Int) {
    constructor() : this(title="Recycler Title",desc = "Recycler Description",image = R.mipmap.ic_launcher)
}