package com.stephan.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.stephan.androidtrivia.databinding.FragmentMyNewBinding

class MyNewFragment : Fragment()
{
    private lateinit var binding: FragmentMyNewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_new,container,false)

        binding.myTextView.text = "My New Fragment"

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val option = NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController())
        return super.onOptionsItemSelected(item)
    }
}