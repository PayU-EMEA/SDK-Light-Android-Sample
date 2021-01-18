package com.payu.android.front.sdk.demo.repository

import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.demo.model.RollModel
import java.util.*

class ProductRepository {

    /**
     * Simple, hardcoded data for list
     * Icons are stored locally
     *
     * @return [RollModel]
     */
    fun provideData() = ArrayList<RollModel>().apply {
            add(RollModel(R.mipmap.hot_dog, "Cena: 1,01 zł", 101, "Hot Dog"))
            add(RollModel(R.mipmap.pizza, "Cena: 10,01 zł", 1001, "Pizza"))
            add(RollModel(R.mipmap.fish, "Cena 50,01 zł", 5001, "Ryba"))
            add(RollModel(R.mipmap.donut, "Cena 100,01 zł", 10001, "Donut"))
            add(RollModel(R.mipmap.watermelon, "Cena 4,00 zł", 400, "Arbuz"))
        }
}
