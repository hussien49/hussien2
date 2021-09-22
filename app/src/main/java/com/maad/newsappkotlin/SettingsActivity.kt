package com.maad.newsappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val radioGroup: RadioGroup = findViewById(R.id.rg)

        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        val savedCountry = pref.getString("code", "us")
        when(savedCountry){
            "us" -> radioGroup.check(R.id.rb_us)
            "de" -> radioGroup.check(R.id.rb_de)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rb_us -> saveCountry("us")
                R.id.rb_de -> saveCountry("de")
            }

        }

    }

    fun saveCountry(countryCode: String) {
        val pref = getSharedPreferences("settings", MODE_PRIVATE).edit()
        pref.putString("code", countryCode)
        pref.apply()
    }

}