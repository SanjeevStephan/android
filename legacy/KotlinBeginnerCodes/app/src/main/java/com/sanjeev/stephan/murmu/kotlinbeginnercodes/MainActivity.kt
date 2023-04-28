package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 12 - Jan - 20
 */

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.layout_input_box.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayAlert()
        
        //Back Arrow Code Below
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
        
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    /**
     * Takes a title of type String and change the action bar title
     * @see https://devofandroid.blogspot.com/2018/03/change-actionbar-title-of-activity.html
     * @param title
     */
    fun setActionTitle(title : String) {
        val action_bar = supportActionBar
        action_bar!!.title = title
    }

    /**
     * This method popups new Input Dialog Box.
     * When User enters its name then the text is displayed in the action bar
     * as "Hello, Sanjeev Stephan Murmu"
     */
    private fun displayAlert() {

        val view = LayoutInflater.from(this).inflate(R.layout.layout_input_box, null, false)
        val inputBox = view.id_dialog_input_box

        val alertBox = AlertDialog.Builder(this)
            .setTitle("What is your Name ?")
            .setView(view)
            .setPositiveButton("Submit") {
                //  dialog: DialogInterface?, which: Int ->
                _: DialogInterface?, _: Int ->

                val name = inputBox.text.toString()
                setActionTitle("Hello, $name")
            }
            .setNegativeButton("Cancel"){
                dialog: DialogInterface?, _: Int ->
                dialog!!.cancel()
                setMyToast(this,"Cancelled")
            }
        alertBox.show()
    }
}
