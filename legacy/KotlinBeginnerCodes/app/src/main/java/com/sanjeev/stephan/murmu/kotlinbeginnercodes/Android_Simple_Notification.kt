package com.sanjeev.stephan.murmu.kotlinbeginnercodes

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentNotificationBinding
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentSimpleNotificationBinding

class Android_Simple_Notification : Fragment() {

    lateinit var binding: FragmentSimpleNotificationBinding
    lateinit var button: Button
    private var notificationManager: NotificationManager? = null

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_notification, null, false)
        return binding.root
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = binding.idNotificationButton
        button.setOnClickListener { launchNotification(view) }
    }

    @SuppressLint("InlinedApi")
    private fun createNotificationChannel(id: String, name: String, desc: String) {
        val importance = NotificationManager.IMPORTANCE_LOW

        val channel = NotificationChannel(id, name, importance)

        channel.description = desc
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        // Register the channel with the System.
        notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager?.createNotificationChannel(channel)


    }

    @SuppressLint("NewApi")
     fun launchNotification(view: View) {
        val channelID = "com.sanjeev.stephan.murmu.kotlinbeginnercodes"
        val notificationID = 101
        val cTitle = "Kotlin App Notification"
        val cText  = "This is a simple Notification"

        val intent = Intent(context as Activity,MainActivity::class.java)

        val pendingIntent : PendingIntent = PendingIntent.getActivity(context as Activity,0,intent,0)

        val notification = Notification.Builder(context as Activity, channelID)
            .setContentTitle(cTitle) // head of the notification
            .setContentText(cText) //body of the notification.
            .setSmallIcon(R.mipmap.ic_launcher)
            .setChannelId(channelID)
            // Set the intent that will fire when the user taps the notification.
            .setContentIntent(pendingIntent)
            //.setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Issue the Notification
        with(NotificationManagerCompat.from(context as Activity))
        {
            /**
             * NotificationID is a unique int for each notification that
             * you must define.
             */
            notify(notificationID,notification.build())
        }

        createNotificationChannel(channelID,cTitle,cText)
    }

}