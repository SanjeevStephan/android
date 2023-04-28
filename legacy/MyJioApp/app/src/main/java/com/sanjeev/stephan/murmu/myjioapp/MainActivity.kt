package com.sanjeev.stephan.murmu.myjioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var toolbar : ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar


        val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        toolbar?.setTitle("My Jio Home")

    }

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
      item ->

      when(item.itemId)
      {
          R.id.id_nav_home -> {
              toolbar?.setTitle("Home")
              return@OnNavigationItemSelectedListener true
          }
          R.id.id_nav_recharge -> {
              toolbar?.setTitle("Recharge")
              return@OnNavigationItemSelectedListener true
          }
          R.id.id_nav_voucher -> {
              toolbar?.setTitle("My Vouchers")
              return@OnNavigationItemSelectedListener true
          }
          R.id.id_nav_coupons -> {
              toolbar?.setTitle("Coupons")
              return@OnNavigationItemSelectedListener true
          }
          R.id.id_nav_jio_care -> {
              toolbar?.setTitle("JioCare")
              return@OnNavigationItemSelectedListener true
          }
      }
      false

  }


}

