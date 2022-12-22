package com.cheesypc.adlib

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


fun Admobinit(context: Context){
    MobileAds.initialize(context)
}
@Composable
fun CustomBannerAdmob(bannerID: String,adSize: AdSize,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context).apply {
                setAdSize(adSize)
                adUnitId = bannerID
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = modifier
    )
}

@Composable
fun BannerAdmob(bannerID: String,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = bannerID
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = modifier
    )
}

@Composable
fun FullBannerAdmob(bannerID: String,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context).apply {
                setAdSize(AdSize.FULL_BANNER)
                adUnitId = bannerID
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = modifier
    )
}

@Composable
fun RectangleBannerAdmob(bannerID: String,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context).apply {
                setAdSize(AdSize.MEDIUM_RECTANGLE)
                adUnitId = bannerID
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = modifier
    )
}


object Ads {






    private var interstitialAd: InterstitialAd? = null
    fun initInterstitialAdmob() {
        interstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d("Ads.kt", "Ad was dismissed.")
            }


            override fun onAdShowedFullScreenContent() {
                Log.d("Ads.kt", "Ad showed fullscreen content.")
                interstitialAd = null
            }
        }
    }

    fun loadInterstitialAdmob(interstitialId:String,context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, interstitialId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("Ads.kt", adError.message)
                interstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("Ads.kt", "Ad was loaded.")
                Ads.interstitialAd = interstitialAd
            }
        })
    }

    fun showIntersitialAdmob(activity: Activity) {
        interstitialAd?.show(activity)
    }
}