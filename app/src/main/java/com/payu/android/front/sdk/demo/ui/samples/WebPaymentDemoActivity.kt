package com.payu.android.front.sdk.demo.ui.samples

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails
import com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails
import com.payu.android.front.sdk.payment_library_webview_module.web.service.WebPaymentService

private const val REDIRECT_URL = "https://merch-prod.snd.payu.com/np/newpayment.resume.action?paymentId=73398264&hash=38b8abab0314dacc883110610f25499e&js=1"

class WebPaymentDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authorizationDetails = AuthorizationDetails.Builder()
                .withLink(REDIRECT_URL)
                .build()

        WebPaymentService.pay(this, authorizationDetails)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == WebPaymentService.REQUEST_CODE) {
            val paymentDetails: PaymentDetails? = data?.getParcelableExtra(WebPaymentService.INTENT_WEB_PAYMENT_EXTRA)

            println(paymentDetails.toString())
            finish()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
