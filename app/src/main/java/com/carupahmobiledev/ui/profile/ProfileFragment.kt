package com.carupahmobiledev.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.R
import com.carupahmobiledev.databinding.FragmentDetectBinding
import com.carupahmobiledev.databinding.FragmentProfileBinding
import com.carupahmobiledev.ui.auth.LoginActivity
import com.carupahmobiledev.ui.detection.DetectViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
            return
        }
        binding.logoutAcc.setOnClickListener { signOut()}
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(requireActivity(), LoginActivity::class.java))
        requireActivity().finish()
    }
}