package com.sanjeev.stephan.murmu.kotlinbeginnercodes


/**
 * @author Sanjeev Stephan Murmu
 * @since 16th-Jan-20
 */

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentButtonBinding

/**
 * This class Display a fragment with a button at the CENTER of the Relative Layout Screen.
 * When the Button is clicked the one is added to the counter
 * Which then displays the changes as on the button text.
 * if the counter is below 10 then the color of the Button will be GREEN
 * if the counter is between 11-19 then the color of the Button will be RED
 * As the counter reaches 20 .it is reset to zero again.
 *
 * Necessary Class Files
 * @see [ Android_Button ]
 * @see [ R.layout.fragment_button ]
 */
class Android_Button : Fragment() {

    lateinit var binding: FragmentButtonBinding
    lateinit var button: Button
    var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_button, null, false)
        return binding.root
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //referencing the ID of the widget from the data-binding instances.
        button = binding.idAndroidButton
        button.setOnClickListener { addCounter() }

    }

    /**
     * Every time this method is called '1' is added to the counter
     * Then Button displays the text 'Counter : $count' with 'count' as Integer Value.
     * such as 'Counter : 1'
     */
    private fun addCounter() {

        counter += 1 // i.e count = count + 1 | adding one to count every time this function is called.
        button.text = "Counter : $counter"

        //if the counter is below 10 then the color of the button will turn GREEN
        if(counter < 10)
        {
            button.setBackgroundColor(Color.GREEN)
        }
        //if the counter is between 11-19 then the color of the button will turn RED
        else if(counter in 11..19)
        {
            button.setBackgroundColor(Color.RED)
        }
        //When the count value reaches '20' then the text of the button will be changed and count value to '0'
        else if(counter == 20)
        {
          button.setBackgroundColor(Color.BLUE)
          button.text = "Counter Reset to Zero"
          counter  = 0
        }

    }


}