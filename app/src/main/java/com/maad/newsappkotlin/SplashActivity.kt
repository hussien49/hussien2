package com.maad.newsappkotlin

import android.content.Intent
import android.util.Log
import com.daimajia.androidanimations.library.Techniques
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

class SplashActivity : AwesomeSplash() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun initSplash(configSplash: ConfigSplash?) {

        MobileAds.initialize(this) {}

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })

        //Customize Circular Reveal
        configSplash?.backgroundColor = R.color.purple_500 //any color you want form colors.xml
        configSplash?.animCircularRevealDuration = 3000 //int ms
        configSplash?.revealFlagX = Flags.REVEAL_RIGHT  //or Flags.REVEAL_LEFT
        configSplash?.revealFlagY = Flags.REVEAL_BOTTOM //or Flags.REVEAL_TOP

        //Customize Logo
        configSplash?.logoSplash = R.mipmap.ic_launcher //or any other drawable
        configSplash?.animLogoSplashDuration = 3000 //int ms
        configSplash?.animLogoSplashTechnique =
            Techniques.RubberBand //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash?.titleSplash = "News Application"
        configSplash?.titleTextColor = R.color.white
        configSplash?.titleTextSize = 30f //float value
        configSplash?.animTitleDuration = 3000
        configSplash?.animTitleTechnique = Techniques.FlipInX
    }

    override fun animationsFinished() {

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                openActivity()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                openActivity()
            }

            override fun onAdShowedFullScreenContent() {
                mInterstitialAd = null
            }
        }

        if (mInterstitialAd != null)
            mInterstitialAd?.show(this)
        else
            openActivity()

    }

    fun openActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}
