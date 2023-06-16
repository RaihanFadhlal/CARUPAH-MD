package com.carupahmobiledev.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.HomeActivity
import com.carupahmobiledev.data.repo.DetectRepo
import com.carupahmobiledev.data.repo.ProfileRepo
import com.carupahmobiledev.databinding.FragmentProfileBinding
import com.carupahmobiledev.ui.detection.DetectViewModel
import com.carupahmobiledev.ui.detection.DetectViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profileRepo : ProfileRepo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileRepo = ProfileRepo()
        profileViewModel = ViewModelProvider(requireActivity(), ProfileViewModelFactory(profileRepo)) [ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutAcc.setOnClickListener{
            (requireActivity() as HomeActivity).signOut()}
    }

}