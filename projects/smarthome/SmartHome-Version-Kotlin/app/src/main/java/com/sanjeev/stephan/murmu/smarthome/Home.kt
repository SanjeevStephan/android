package com.sanjeev.stephan.murmu.smarthome


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.sanjeev.stephan.murmu.smarthome.sample.SampleDevice

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //findNavController().navigate(R.id.action_home_to_sampleDevice)

        val fragOne = SampleDevice()
        val fragTwo = SampleDevice()
        val fragThree = SampleDevice()

        doFragmentTransaction(R.id.idFragmentContainer,"First","Light Bulb")
        doFragmentTransaction(R.id.idFragmentContainerSecond,"Second","Fan")
        doFragmentTransaction(R.id.idFragmentContainerThird, "Third", "Switch")

        /*
        doFragmentTransaction(R.id.idFragmentContainer,fragOne,"First","Light Bulb")
        doFragmentTransaction(R.id.idFragmentContainerSecond,fragTwo,"Second","Fan")
        doFragmentTransaction(R.id.idFragmentContainerThird, fragThree, "Third", "Switch")

         */
    }

    private fun doFragmentTransaction(containerId : Int,msg : String,title: String) {

        val show  = Show(view?.context as Activity,"Home")

        val bundle : Bundle = bundleOf("title" to title)

        //Sample Fragment
        val sample: SampleDevice = SampleDevice()
        //sending bundle as arguments
        sample.arguments = bundle
        //Get Supported Fragment Manager
        val fragmentMgr: FragmentManager? = fragmentManager
        //Do the Transaction
        val fragmentTrans: FragmentTransaction? = fragmentMgr?.beginTransaction()
        //Add the Fragment to the container(R.id.idFragmentContainer)
        fragmentTrans?.add(containerId, sample)
        fragmentTrans?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        //Commit Change
        fragmentTrans?.commit()
        show.setLog("$msg Fragment Commited-Now")
    }

}
