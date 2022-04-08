package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 16th-Jan-20
 */

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

/**
 * This class takes the context as a arguments in the class default constructor
 * when the class is called to show the alert dialog
 *
 * @see Android_Dialog_Alert
 */
class Android_Dialog_Alert(context: Context) {


    /**
     * This init{ block } is called automatically when this class is called!.
     * its like an Auto-Initializing Block Code.
     * Anything inside this init { block } is automatically gets executed when class is called.
     */
    init {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Did you click the list Item")

        dialog.setPositiveButton("Yes,I do") {
            dialog: DialogInterface?, which: Int ->
            // do something when the positive(+ve) button is clicked
            setMyToast(context as Activity,"Yes I have clicked the Button\"")
        }

        dialog.setNegativeButton("No,I don't") {
            dialog: DialogInterface?, which: Int ->
            // do something when the negative(-ve) button is clicked
            setMyToast(context as Activity,"No I din't clicked the Button")
        }

        dialog.setNeutralButton("Cancel") {
            dialog: DialogInterface?, which: Int ->
            // do something when the neutral button is clicked
            setMyToast(context as Activity,"Cancelled")
        }

        dialog.show() //dont forget to call the 'show()' method on the dialog object instance or it won't display
    }


}