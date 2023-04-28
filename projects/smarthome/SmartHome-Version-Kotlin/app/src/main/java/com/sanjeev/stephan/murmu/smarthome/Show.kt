package com.sanjeev.stephan.murmu.smarthome

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast


class Show(private val context : Activity, private val location: String) {
    private val tagName = "TAG"
    private var methodName = " "

    fun setLog(msg: String) {
        Log.i(tagName, "$location=>$methodName$msg")
    }

    fun setToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}