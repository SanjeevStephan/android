package com.sanjeev.stephan.murmu.kotlinbeginnercodes


/**
 * @author Sanjeev Stephan Murmu
 * @since 14th-Jan-20
 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentEdittextTextviewBinding

class Android_EditText_TextView : Fragment() {

    //data-binding variable
    private lateinit var binding: FragmentEdittextTextviewBinding
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var editText: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edittext_textview, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = binding.button
        textView = binding.textView
        editText = binding.editText

        button.setOnClickListener { onBtnClick() }
    }

    private fun onBtnClick() {
        val text = editText.text.toString()
        textView.text = text

        Log.i("TAG","Text Received : $text")
    }
}