package com.carupahmobiledev.ui.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.HomeActivity
import com.carupahmobiledev.R
import com.carupahmobiledev.data.TokenPreferences
import com.carupahmobiledev.data.remote.response.Data
import com.carupahmobiledev.data.repo.AuthRepo
import com.carupahmobiledev.databinding.ActivityRegisterBinding
import com.carupahmobiledev.util.ViewModelFactory
import com.carupahmobiledev.util.showLoading
import com.carupahmobiledev.util.showToast

class RegisterActivity : AppCompatActivity() {
    private lateinit var regisBinding: ActivityRegisterBinding
    private lateinit var tokenPref: TokenPreferences
    private lateinit var authRepo: AuthRepo
    private lateinit var authViewModel: AuthViewModel
    private lateinit var id : Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regisBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(regisBinding.root)
        supportActionBar?.hide()

        tokenPref = TokenPreferences(this)
        authRepo = AuthRepo()
        authViewModel = ViewModelProvider(this, ViewModelFactory(tokenPref, authRepo, this)) [AuthViewModel::class.java]

        regisBinding.buttonJoin.setOnClickListener { userRegister() }

        setAnimation()
    }

    private fun userRegister() {
        val username = regisBinding.inputName.text.toString().trim()
        val email = regisBinding.inputEmail.text.toString().trim()
        val numberPhone = regisBinding.inputPhone.text.toString().trim()
        val password = regisBinding.inputPass.text.toString().trim()
        val confirm_pw = regisBinding.inputConfPass.text.toString().trim()

        authViewModel.isLoading.observe(this) { isLoading ->
            showLoading(regisBinding.progressBar, isLoading)
        }

        when {
            username.isEmpty() -> {
                regisBinding.inputName.error = getString(R.string.must_filled)
            }
            email.isEmpty() -> {
                regisBinding.inputEmail.error = getString(R.string.must_filled)
            }
            numberPhone.isEmpty() -> {
                regisBinding.inputPhone.error = getString(R.string.must_filled)
            }
            password.isEmpty() -> {
                regisBinding.inputPass.error = getString(R.string.must_filled)
            }
            confirm_pw.isEmpty() -> {
                regisBinding.inputConfPass.error = getString(R.string.must_filled)
            }
            password.length < 8 -> {
                regisBinding.inputPass.error = getString(R.string.min_password)
            }
            !email.matches(emailPattern) -> {
                regisBinding.inputEmail.error = getString(R.string.not_matched)
            }
            else -> {
                authViewModel.register(username, email, password, confirm_pw, numberPhone)
                authViewModel.regMessage.observe(this) {
                    it.getContentIfNotHandled()?.let {
                        showToast(this, getString(R.string.email_taken))
                    }
                }
                authViewModel.registerUser.observe(this) { register ->
                    if (register != null) {
                        val id = register.data.uid
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.putExtra("ID", id)
                        finish()
                        showToast(this, getString(R.string.succeed))
                    }
                }
            }
        }
    }
    private fun setAnimation() {
        val title = ObjectAnimator.ofFloat(regisBinding.title1, View.ALPHA, 1f).setDuration(300)
        val title2 = ObjectAnimator.ofFloat(regisBinding.title2, View.ALPHA, 1f).setDuration(300)
        val name = ObjectAnimator.ofFloat(regisBinding.inputName, View.ALPHA, 1f).setDuration(300)
        val email = ObjectAnimator.ofFloat(regisBinding.inputEmail, View.ALPHA, 1f).setDuration(300)
        val phone = ObjectAnimator.ofFloat(regisBinding.inputPhone, View.ALPHA, 1f).setDuration(300)
        val password = ObjectAnimator.ofFloat(regisBinding.inputPass, View.ALPHA, 1f).setDuration(300)
        val confirm_pw = ObjectAnimator.ofFloat(regisBinding.inputConfPass, View.ALPHA, 1f).setDuration(300)
        val btnRegister = ObjectAnimator.ofFloat(regisBinding.buttonJoin, View.ALPHA, 1f).setDuration(300)
        val btnGoogle = ObjectAnimator.ofFloat(regisBinding.joinGoogle, View.ALPHA, 1f).setDuration(300)

        val together = AnimatorSet().apply {
            playTogether(name, email, phone, password, confirm_pw)
        }
        val togetherSecond = AnimatorSet().apply {
            playTogether(btnGoogle, btnRegister)
        }
        AnimatorSet().apply {
            playSequentially(title, title2, together, togetherSecond)
            start()
        }
    }

    companion object{
        val emailPattern = Regex("[a-zA-Z\\d._]+@[a-z]+\\.+[a-z]+")
    }
}