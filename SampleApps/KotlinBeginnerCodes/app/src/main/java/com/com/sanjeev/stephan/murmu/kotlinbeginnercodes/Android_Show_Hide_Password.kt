package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 @author Sanjeev Stephan Murmu
 @since 2nd-Febuary-2020
**/

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

/**
 * A simple [Android_Show_Hide_Password] subclass.
 * This Fragment class 'shows and hides' the password from the EditText.
 * @see [R.layout.fragment_android__show__hide__password]
 * @see https://www.tutorialkart.com/kotlin-android/android-show-hide-password-in-edittext/
 */
class Android_Show_Hide_Password : Fragment() {

    private lateinit var inputPassword : EditText
    private lateinit var showHideBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android__show__hide__password, container, false)
    }


    /**
    Override the onViewCreated Method()
    @param view 
    @param savedInstance
    **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputPassword = view.findViewById(R.id.id_password_edit_text)
        showHideBtn = view.findViewById(R.id.id_showHideBtn) //this is an image button

        showHideBtn.setOnClickListener{

            //if the image button shows an clear eye then execute below code statement.
            if(showHideBtn.text.toString().equals("show")) {
                inputPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                //show the hidden visibility icon image
                //showHideImgBtn.setImageResource(R.drawable.ic_visibility_off_black)
                showHideBtn.text = "hide"
            }
            else {
                inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                //show the visibility icon image
                //showHideBtn.setImageResource(R.drawable.ic_visibility_black)
                showHideBtn.text = "show"
            }
        }
    }

}
