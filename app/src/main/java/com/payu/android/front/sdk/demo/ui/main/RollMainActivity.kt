package com.payu.android.front.sdk.demo.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.github.nitrico.lastadapter.Holder
import com.github.nitrico.lastadapter.LastAdapter
import com.payu.android.front.sdk.demo.model.RollModel
import com.payu.android.front.sdk.demo.ui.base.ActivityWithMenu
import com.payu.android.front.sdk.demo.ui.base.ChangeThemeDialog
import com.payu.android.front.sdk.demo.ui.samples.*
import com.payu.android.front.sdk.demo.ui.summary.RollSummaryActivity
import com.payu.android.front.sdk.frontsdk.BR
import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.frontsdk.databinding.ActivityMainRollBinding
import com.payu.android.front.sdk.frontsdk.databinding.ItemRollToBuyBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

const val BUNDLE_DATA = "ROLL_MODEL"

class RollMainActivity : ActivityWithMenu() {
    @Inject
    lateinit var viewModel: RollMainViewModel

    private lateinit var binding: ActivityMainRollBinding

    private fun setupToolbar() {
        setSupportActionBar(binding.payuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun handleOnItemClick(itemBinding: Holder<ItemRollToBuyBinding>) {
        startActivity(
                Intent(this, RollSummaryActivity::class.java)
                        .apply { putExtra(BUNDLE_DATA, itemBinding.binding.item) }
        )
    }

    private fun observeProducts() {
        viewModel.productFetchedEvent.observe(this, Observer {
            it?.let {items ->
                LastAdapter(items, BR.item)
                        .map<RollModel, ItemRollToBuyBinding>(R.layout.item_roll_to_buy) {
                            onClick { item -> handleOnItemClick(item) }
                        }
                        .into(binding.rollProductsRecyclerView)
            }
        })
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_roll)

        setupToolbar()

        viewModel.fetchProducts()

        observeProducts()
    }
}
