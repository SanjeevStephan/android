package com.sanjeev.stephan.murmu.kotlinbeginnercodes.recyclerview

import com.sanjeev.stephan.murmu.kotlinbeginnercodes.R

data class MyDataModel(val title: String, val desc: String, val image: Int) {
    constructor() : this(title = "Simple Title", desc = "Description", image = R.mipmap.ic_launcher)
}
