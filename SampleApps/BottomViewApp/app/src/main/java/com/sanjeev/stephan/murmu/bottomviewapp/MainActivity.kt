package com.sanjeev.stephan.murmu.bottomviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var toolbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar

        val navigation = findViewById<View>(R.id.id_bottom_navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar?.setTitle("Bottom View App")
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item ->

        when(item.itemId)
        {
            R.id.id_nav_frag_a -> {
                toolbar?.setTitle("Fragment A")

                val fragA = FragmentA()
                loadFragment(fragA)

                return@OnNavigationItemSelectedListener true
            }
            R.id.id_nav_frag_b -> {
                toolbar?.setTitle("Fragment B")

                val fragB = FragmentB()
                loadFragment(fragB)

                return@OnNavigationItemSelectedListener true
            }
            R.id.id_nav_frag_c -> {
                toolbar?.setTitle("Fragment B")
                
                val fragC = FragmentC()
                loadFragment(fragC)

                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun loadFragment(fragment : Fragment){
        val fragTrans = supportFragmentManager
        val fragMgr = fragTrans.beginTransaction()
        fragMgr.replace(R.id.frag_container,fragment)
        fragMgr.commit()
    }

}
