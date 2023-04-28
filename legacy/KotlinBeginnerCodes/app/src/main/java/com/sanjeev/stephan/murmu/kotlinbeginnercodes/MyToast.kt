package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 13th-Jan-20
 * @see FragmenetHome
 */
import android.app.Activity
import android.widget.Toast

/**
 * Takes String Text Message and Display it as Toast Message.
 * @param context from where it is being called !
 * @param msg the message to be displayed in the Toast
 */
fun setMyToast(context: Activity, msg : String){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}