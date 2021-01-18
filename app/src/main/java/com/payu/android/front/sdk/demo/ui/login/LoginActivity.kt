package com.payu.android.front.sdk.demo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.payu.android.front.sdk.demo.ui.main.RollMainActivity
import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.frontsdk.databinding.ActivityLoginBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: LoginViewModel

    private fun observeOnEditorAction(binding: ActivityLoginBinding) {
        binding.clientSecret.setOnEditorActionListener { _, _, _ ->
            viewModel.login()
            true
        }
    }

    private fun observeLoginStatus(binding: ActivityLoginBinding) {
        viewModel.loginEvent.observe(this, Observer { loginSuccessful ->
            if (loginSuccessful == true) {
                startActivity(Intent(this, RollMainActivity::class.java))
                finish()
            } else {
                Snackbar.make(binding.container, R.string.login_failed, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.model = viewModel

        observeOnEditorAction(binding)
        observeLoginStatus(binding)
    }
}
