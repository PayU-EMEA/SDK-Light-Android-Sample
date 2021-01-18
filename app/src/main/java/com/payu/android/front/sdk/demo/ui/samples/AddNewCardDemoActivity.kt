package com.payu.android.front.sdk.demo.ui.samples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.frontsdk.databinding.ActivityAddNewCardBinding
import com.payu.android.front.sdk.payment_add_card_module.service.Error
import com.payu.android.front.sdk.payment_add_card_module.service.NewCardCallback
import com.payu.android.front.sdk.payment_add_card_module.service.NewCardService
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod

const val POS_ID_KEY = "pos_id"

class AddNewCardDemoActivity : AppCompatActivity() {
    private val TAG = AddNewCardDemoActivity::class.java.name

    private val callback = object : NewCardCallback {
        override fun onSuccess(cardPaymentMethod: CardPaymentMethod) {
            Log.v(TAG, "card added, month:" + cardPaymentMethod.cardExpirationMonth + " year: " + cardPaymentMethod.cardExpirationYear + " number Masked: " + cardPaymentMethod.cardNumberMasked)
        }

        override fun onError(error: Error) {
            Log.v(TAG, "Error code" + error.errorCode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddNewCardBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_card)

        val instance = NewCardService.newInstance(binding.newCardWidget, this, callback)

        binding.saveAndUseButton.setOnClickListener {
            instance.addCardWithAgreement(intent.getStringExtra(POS_ID_KEY)!!)
        }
    }
}