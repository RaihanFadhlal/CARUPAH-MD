package com.carupahmobiledev.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.HomeActivity
import com.carupahmobiledev.data.repo.ProfileRepo
import com.carupahmobiledev.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileVM: ProfileViewModel
    private lateinit var profileRepo : ProfileRepo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileRepo = ProfileRepo()
        profileVM = ViewModelProvider(requireActivity(), ProfileViewModelFactory(profileRepo)) [ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            val nilai = bundle.getString("key")
            profileVM.getProfile(nilai!!)
            profileVM.profileUser.observe(requireActivity()) { response ->
                val nama = response.name
                binding.myName.text = nama
            }
        }

        binding.logoutAcc.setOnClickListener{
            (requireActivity() as HomeActivity).signOut()}
    }

}