package com.cheesypc.adlib

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.ads.*


@Composable
fun BannerAdFb(bannerID:String,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context, bannerID, AdSize.BANNER_HEIGHT_50).apply {
                loadAd(this.buildLoadAdConfig().build())
            }
        },
        modifier = modifier
    )
}
@Composable
fun CustomBannerAdFb(bannerID:String,adSize:AdSize,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context, bannerID, adSize).apply {
                loadAd(this.buildLoadAdConfig().build())
            }
        },
        modifier = modifier
    )
}
@Composable
fun RectangleBannerAdFb(bannerID:String,context: Context, modifier: Modifier = Modifier) {
    AndroidView(
        factory = {
            AdView(context, bannerID, AdSize.RECTANGLE_HEIGHT_250).apply {
                loadAd(this.buildLoadAdConfig().build())
            }
        },
        modifier = modifier
    )
}
class FbAds {


    object FbAds {


        private var canLoadAd = true

        private val intertialAdlisiner = object : InterstitialAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                canLoadAd = true
            }

            override fun onAdLoaded(p0: Ad?) {
                canLoadAd = true
            }

            override fun onAdClicked(p0: Ad?) {
            }

            override fun onLoggingImpression(p0: Ad?) {
            }

            override fun onInterstitialDisplayed(p0: Ad?) {
            }

            override fun onInterstitialDismissed(p0: Ad?) {
            }
        }

        private var interstitialAd: InterstitialAd? = null
        fun initInterstitialFb(interstitialId: String,context: Context) {
            interstitialAd = InterstitialAd(context, interstitialId)
        }

        fun loadInterstitialFb() {
            interstitialAd?.also {
                if (canLoadAd) {
                    canLoadAd = false
                    it.loadAd(
                        it.buildLoadAdConfig()
                            .withAdListener(intertialAdlisiner)
                            .build()
                    )
                }
            }

        }

        fun showInterstitialFb() {

            try {
                interstitialAd?.show()
            } catch (_: Exception) {

            }
        }
    }
}