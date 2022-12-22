package com.cheesypc.adlib


import android.app.Application
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds


class App: Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}
        AudienceNetworkAds.initialize(this)

    }
}