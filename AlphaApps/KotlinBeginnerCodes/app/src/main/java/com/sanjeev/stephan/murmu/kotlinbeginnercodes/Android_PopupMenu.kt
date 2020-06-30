package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 15-Feb-2020
 */

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class Android_PopupMenu : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android_popup_menu, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.idPopupMenu)
        button.setOnClickListener() {
            buildPopupMenu(it)
        }
    }

    private fun buildPopupMenu(parent : View){

        val popup = PopupMenu(context as Activity,parent)
        popup.setOnMenuItemClickListener {
            it ->

            when (it.itemId) {
                R.id.id_menu_open -> {
                    setToast(view?.context as Activity,"Open Menu Clicked")
                    true
                }
                R.id.id_menu_source_code -> {
                    setToast(view?.context as Activity,"Source Codes Clicked")
                    true
                }
                R.id.id_menu_about -> false
                else -> false
            }
        }

        popup.inflate(R.menu.popup_menu)

        try {
           val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popup)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception) {
            Log.e("Main", "Error Showing Menu Icons.", e)
        } finally {
            popup.show()
        }
    }

}
