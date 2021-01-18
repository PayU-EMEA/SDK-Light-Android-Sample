package com.payu.android.front.sdk.demo.ui.samples

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.payu.android.front.sdk.payment_library_core_android.about.AboutActivity


class AboutDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, AboutActivity::class.java))
        finish()
    }
}
