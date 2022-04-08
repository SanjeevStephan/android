package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 18th-Jan-2020
 */
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_send_data.view.*

/**
 * Pass data between destinations with Bundle objects
 * @see https://developer.android.com/guide/navigation/navigation-pass-data
 */
class Android_SenderFragment : Fragment() {
    lateinit var inputBox: EditText
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_data, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputBox = view.id_sender_edit_text
        button = view.id_sender_button

        button.setOnClickListener { sendData2Receiver() }


    }

    /**
     * Create a Bundle object and pass it to the destination using navigate(), as shown below:
     */
    private fun sendData2Receiver() {
        val text = inputBox.text.toString()
        //here 'data' is the KEY | 'text' is the VALUE to be sent.
        val bundle: Bundle = bundleOf("data" to text)

        view!!.findNavController().navigate(R.id.action_android_SenderFragment_to_android_ReceiverFragment,bundle)
        setMyToast(context as Activity,"Data Successfully Received!")
    }

}