package com.carupahmobiledev.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.HomeActivity
import com.carupahmobiledev.R
import com.carupahmobiledev.data.TokenPreferences
import com.carupahmobiledev.data.repo.AuthRepo
import com.carupahmobiledev.databinding.ActivityLoginBinding
import com.carupahmobiledev.util.ViewModelFactory
import com.carupahmobiledev.util.showLoading
import com.carupahmobiledev.util.showToast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var tokenPref: TokenPreferences
    private lateinit var authRepo: AuthRepo
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        tokenPref = TokenPreferences(this)
        authRepo = AuthRepo()
        authViewModel = ViewModelProvider(this, ViewModelFactory(tokenPref, authRepo, this)) [AuthViewModel::class.java]

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        binding.loginGoogle.setOnClickListener {
            signIn()
        }
        binding.openReg.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        binding.buttonLogin.setOnClickListener {
            userLogin()
        }
    }

    // Login Google
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }
    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    // Login Custom
    private fun userLogin() {
        val email = binding.inputEmailLog.text.toString().trim()
        val password = binding.inputPassLog.text.toString().trim()

        authViewModel.isLoading.observe(this) { isLoading ->
            showLoading(binding.progressBar, isLoading)
        }

        when {
            email.isEmpty() -> {
                binding.inputEmailLog.error = getString(R.string.must_filled)
            }
            password.isEmpty() -> {
                binding.inputPassLog.error = getString(R.string.must_filled)
            }

            !email.matches(RegisterActivity.emailPattern) -> {
                binding.inputEmailLog.error = getString(R.string.not_matched)
            }
            else -> {
                authViewModel.login(email, password)
                authViewModel.logMessage.observe(this) {
                    it.getContentIfNotHandled()?.let {
                        showToast(this, getString(R.string.login_failed))
                    }
                }
                authViewModel.loginUser.observe(this) { login ->
                    val uid = intent.getIntExtra("ID", 0)
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("UID", uid)
                    startActivity(intent)
                    showToast(this, "${getString(R.string.login_succeed)} ${login.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}