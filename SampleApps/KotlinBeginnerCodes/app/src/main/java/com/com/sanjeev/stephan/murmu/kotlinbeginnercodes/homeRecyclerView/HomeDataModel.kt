package com.sanjeev.stephan.murmu.kotlinbeginnercodes.homeRecyclerView

import com.sanjeev.stephan.murmu.kotlinbeginnercodes.R

class HomeDataModel(val title: String,val desc: String,val image: Int,val isCompleted: Boolean) {
    constructor() : this(title ="Simple Title",desc = "Simple Desc",image = R.drawable.ic_adb_green,isCompleted = false) }