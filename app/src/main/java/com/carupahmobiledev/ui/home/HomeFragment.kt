package com.carupahmobiledev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.HomeActivity
import com.carupahmobiledev.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeFragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return homeFragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentBinding.bsButton.setOnClickListener {
            (requireActivity() as HomeActivity).moveToLocationFragment()
        }
    }
}