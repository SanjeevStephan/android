package com.sanjeev.stephan.murmu.kotlinrecyclerview

/**
 * @author Sanjeev Stephan Murmu
 * @since 08-Feb-2020
 *
 * This Class is used to  Model the Data using the ArrayList to Structure the Data in the Organized way.
 */
data class MyDataModel(val image: Int,val title: String,val desc: String)
{
    constructor() : this(image = R.drawable.ic_launcher_background,title = "Simple Title",desc = "Simple Description")
}