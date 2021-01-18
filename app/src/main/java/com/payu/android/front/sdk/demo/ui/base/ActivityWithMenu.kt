package com.payu.android.front.sdk.demo.ui.base

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.payu.android.front.sdk.demo.ui.samples.AboutDemoActivity
import com.payu.android.front.sdk.frontsdk.R

abstract class ActivityWithMenu : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_demo_samples, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sample_about -> {
            startActivity(Intent(this, AboutDemoActivity::class.java))
            true
        }
        R.id.action_change_theme -> {
            ChangeThemeDialog.showThemeDialog(supportFragmentManager)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}