package com.sanjeev.stephan.murmu.kotlinbeginnercodes

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText

/**
 * This class displays as Input Dialog Box
 * which ask Username to Enter in the input box
 * and then displays it as a Toast Message
 *
 * Necessary Class Files
 * @see Android_Dialog_Input
 * @see R.layout.layout_input_box
 */
class Android_Dialog_Input(context: Context) {
    init {
        val myDialog = AlertDialog.Builder(context)
        myDialog.setTitle("Enter User name !")
        myDialog.setIcon(R.mipmap.ic_launcher)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_input_box, null, false)
        val editText = view.findViewById<EditText>(R.id.id_dialog_input_box)
        editText.setHint("Sanjeev Stephan Murmu")
        myDialog.setView(view)
        myDialog.setPositiveButton("Submit"){
            // dialog: DialogInterface?, which: Int ->
            _: DialogInterface?, _: Int ->
            setMyToast(context as Activity,"Username : ${editText.text}")
        }
        myDialog.setNegativeButton("Cancelled") {
                //dialog: DialogInterface?, which: Int ->
        dialog: DialogInterface?, _: Int ->

            dialog!!.cancel()
            setMyToast(context as Activity,"Cancelled")
        }

        myDialog.show()

    }
}