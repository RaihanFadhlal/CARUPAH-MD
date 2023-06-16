package com.carupahmobiledev.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {

    private lateinit var locationFragmentBinding: FragmentLocationBinding
    private lateinit var locationViewModel: LocationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        locationFragmentBinding = FragmentLocationBinding.inflate(inflater, container, false)
        locationViewModel = ViewModelProvider(requireActivity())[LocationViewModel::class.java]
        return locationFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}