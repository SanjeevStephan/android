package com.sanjeev.stephan.murmu.kotlinbeginnercodes.homeRecyclerView

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.*
import java.lang.Exception

class HomeRecyclerAdapter(val context: Activity,val arrayList: ArrayList<HomeDataModel>) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_recycler_view_row_item, parent, false)
        val viewHolder = HomeViewHolder(view,listener)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val model: HomeDataModel = arrayList.get(position)
        holder.title.text = model.title
        holder.desc.text = model.desc
        holder.image.setImageResource(model.image)
        holder.switch.isChecked = model.isCompleted //TODO : check or uncheck the Switch from Here.
        holder.switch.isEnabled = false //TODO : enable or disable the Switch from Here.

        holder.itemView.setOnClickListener() {
          buildPopupMenu(it,position)

          /*  val home = Home()
            home.HomePopupMenu(it,position)*/
        }

    }

    private lateinit var listener: OnMyItemClickListener

    fun setOnMyItemClickListener(listener: OnMyItemClickListener) {
        this.listener = listener
    }

    interface OnMyItemClickListener {
        fun onMyItemClickListener(position: Int)
        fun onMyImageClickListener(position: Int)
        fun onMySwitchClickListener(position: Int)
    }

    /**
     * Takes the [Int] value and filter the action based on the number received.
     * @param itemPosition integer value of the item position.
     */
    private fun chooseItem(parentView: View,itemPosition: Int) {
        when (itemPosition) {

            2 -> navigateToFragment(R.id.action_fragmentHome_to_fragment_EditText_TextView,parentView) //navigate to Android_EditText_TextView Fragment.
            3 -> navigateToFragment(R.id.action_fragmentHome_to_android_Access_URL_WhatsApp,parentView)
            7 -> navigateToFragment(R.id.action_fragmentHome_to_android_Button,parentView) //navigate to Android_Button fragment
            9 -> {
                val main = MainActivity()
                main.setActionTitle("hello World")
            }
            14 -> Android_Dialog_Alert(context as Activity) //this will call the Alert-Dialog box
            15 -> Android_Dialog_Input(context as Activity)
            33 -> navigateToFragment(R.id.action_fragmentHome_to_fragment_Notification_Category,parentView)
            35 -> navigateToFragment(R.id.action_fragmentHome_to_android_SenderFragment,parentView)
            37 -> navigateToFragment(R.id.action_fragmentHome_to_android_PopupMenu,parentView)
            42 -> navigateToFragment(R.id.action_fragmentHome_to_android_RecyclerView,parentView)
            45 -> {
                //Below Code will start 'NewActivity.kt' class using the Intent.
                val intent = Intent(context as Activity, NewActivity::class.java)
                context.startActivity(intent)
            }
            47 -> navigateToFragment(R.id.action_fragmentHome_to_android_Spinner,parentView)
            50 -> navigateToFragment(R.id.action_fragmentHome_to_android_SQLiteDatabase,parentView)
            62 -> navigateToFragment(R.id.action_fragmentHome_to_android_WebView,parentView)
            66 -> navigateToFragment(R.id.action_fragmentHome_to_android_ExpandableListView,parentView)
            67 -> navigateToFragment(R.id.action_fragmentHome_to_android_Show_Hide_Password,parentView)
            68 -> navigateToFragment(R.id.action_fragmentHome_to_android_ExpandableWebView,parentView)
        }
    }

    /**
     * @param fragmentActionID Takes the ID of the fragment as Int and Pass it to NaController to Navigate to that fragment.
     */
    private fun navigateToFragment(fragmentActionID: Int,parent: View) {
        parent.findNavController().navigate(fragmentActionID)
    }


    private fun buildPopupMenu(parent: View, position: Int) {

        val topLevel = TopLevelArray()
        val links = topLevel.getLinkList()


        val popup = PopupMenu(context as Activity, parent)
        popup.inflate(R.menu.popup_menu) //TODO : Inflater your own Popup Menu
        popup.setOnMenuItemClickListener { it ->

            when (it.itemId) {
                R.id.id_menu_open -> {
                    setToast(parent.context as Activity, "Opening Fragment..")
                     chooseItem(parent,position)
                    true
                }
                R.id.id_menu_source_code -> {

                    val link = links[position]
                    //TODO : this link is passed to WebView to open web page.
                    setToast(parent.context as Activity, "LINK : $link Opening.")


                    val bundle = bundleOf("url" to link)
                    parent.findNavController().navigate(R.id.action_fragmentHome_to_android_WebView,bundle)

                    true
                }
                R.id.id_menu_about -> false
                else -> false
            }
        }
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