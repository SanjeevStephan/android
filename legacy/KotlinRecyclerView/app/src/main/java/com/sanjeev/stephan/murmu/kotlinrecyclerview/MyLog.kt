package com.sanjeev.stephan.murmu.kotlinrecyclerview

/**
 * @author Sanjeev Stephan Murmu
 * @since 08-Feb-2020
 *
 */
import android.app.Activity
import android.util.Log
import android.widget.Toast

fun setMyLog(msg : String) {
    Log.i("TAG",msg)
}
fun setMyToast(context : Activity,msg : String)
{
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}