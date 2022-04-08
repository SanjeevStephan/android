package com.sanjeev.stephan.murmu.kotlinbeginnercodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentNotificationBinding

class Fragment_Notification_Category : Fragment() {

    lateinit var binding: FragmentNotificationBinding
    lateinit var btn : Button
    lateinit var btn2: Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn = binding.idSimpleNotify
        btn2 = binding.idHeadsUpNotify
        btn3 = binding.idProgressNotify
        btn4 = binding.idReplayNotify

            // var simpleNotification = Android_Simple_Notification()

        btn.setOnClickListener { findNavController().navigate(R.id.android_simpleNotification) }

    }



}