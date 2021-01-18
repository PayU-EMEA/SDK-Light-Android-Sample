package com.payu.android.front.sdk.demo.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.payu.android.front.sdk.frontsdk.R

private const val DAY_MODE_POSITION = 0
private const val NIGHT_MODE_POSITION = 1

const val THEME_DIALOG_TAG = "ThemeDialog"

class ChangeThemeDialog : DialogFragment() {

    companion object {
        fun showThemeDialog(fragmentManager: FragmentManager) {
            ChangeThemeDialog().apply {
                show(fragmentManager, THEME_DIALOG_TAG)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val themeModes = arrayOf(
            resources.getString(R.string.theme_light),
            resources.getString(R.string.theme_dark),
            resources.getString(R.string.theme_default)
        )
        return activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.choose_theme_title)
                .setItems(themeModes) { _, which ->
                    when (which) {
                        DAY_MODE_POSITION -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        NIGHT_MODE_POSITION -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        else -> when (Build.VERSION.SDK_INT) {
                            Build.VERSION_CODES.P -> AppCompatDelegate.setDefaultNightMode(
                                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                            )
                            Build.VERSION_CODES.Q -> AppCompatDelegate.setDefaultNightMode(
                                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                            )
                        }
                    }
                    dismiss()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}