package com.carupahmobiledev.ui.location

import LocationAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carupahmobiledev.data.repo.LocationRepo
import com.carupahmobiledev.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {

    private lateinit var locBinding: FragmentLocationBinding
    private lateinit var locViewModel: LocationViewModel
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var locationRepo : LocationRepo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        locBinding = FragmentLocationBinding.inflate(inflater, container, false)
        locationRepo = LocationRepo()
        locViewModel = ViewModelProvider(requireActivity(), LocViewModelFactory(locationRepo))[LocationViewModel::class.java]
        return locBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationAdapter = LocationAdapter(emptyList())

        locBinding.rvBs.adapter = locationAdapter
        locBinding.rvBs.layoutManager = LinearLayoutManager(requireContext())

        locViewModel.bankSampahList.observe(viewLifecycleOwner) { response ->
            locationAdapter.updateData(response)
        }

        locViewModel.getBankSampahData()
    }
}