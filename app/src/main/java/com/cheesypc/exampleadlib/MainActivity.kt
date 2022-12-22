package com.cheesypc.exampleadlib

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.cheesypc.adlib.*
import com.cheesypc.exampleadlib.ui.theme.AdLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Ads.initInterstitialAdmob()
        Ads.loadInterstitialAdmob("ca-app-pub-3940256099942544/1033173712",this)


        setContent {
            AdLibTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.clickable {
       Ads.showIntersitialAdmob(LocalContext as Activity)

    })
    BannerAdFb(bannerID = "asda", context = LocalContext.current )
    BannerAdmob(bannerID = "ca-app-pub-3940256099942544/6300978111", context = LocalContext.current)

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdLibTheme {
        Greeting("Android")

    }
}