package com.payu.android.front.sdk.demo.config;

import com.payu.android.front.sdk.frontsdk.R;
import com.payu.android.front.sdk.payment_library_core_android.configuration.DefaultStyleConfiguration;

public class CustomPayUStyle extends DefaultStyleConfiguration {


    @Override
    public int payuStyle() {
        return R.style.MerchantStyle;
    }

    @Override
    public int payuLibraryIcon() {
        return R.mipmap.ic_launcher_roll;
    }
}
