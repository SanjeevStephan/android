package com.sanjeev.stephan.murmu.sqliteapplication

import android.app.Activity
import android.util.Log
import android.widget.Toast

fun setToast(context: Activity,msg: String){
    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
}

fun setLog(msg: String){
    Log.i("TAG",msg)
}