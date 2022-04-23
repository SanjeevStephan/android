package com.stephan.androidtrivia

/**
 * @author Stephan
 * @see MainActivity
 */

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.stephan.androidtrivia.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass. with data-binding inflated with option menu on the app bar!.
 * This code works when the ID of the option menu and the destination fragment are SAME.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,R.layout.fragment_title,container,false)

        //transaction from title fragment to game fragment
        binding.playButton.setOnClickListener {
            view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }

        //tells the navController that this fragment has an Option Menu!
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //inflate the menu layout file
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val option = NavigationUI.onNavDestinationSelected(item,view!!.findNavController())
        return option || super.onOptionsItemSelected(item)
    }
}

