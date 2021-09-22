package com.maad.newsappkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
    }

    private fun loadData() {
        val progress: ProgressBar = findViewById(R.id.pb)
        progress.visibility = View.VISIBLE


        //Receiving sent category from Home Activity
     //   val category = intent.getStringExtra("category")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://animanga2.p.rapidapi.com/anime/6124e68881405c2520ab46cc")
            .get()
            .addHeader("x-rapidapi-host", "animanga2.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "9723d68f42mshae97958023c4930p163996jsn572c46c78f4b")
            .build()
        val response = client.newCall(request).execute()
        //Getting saved country from Shared Preference
        val pref = getSharedPreferences("settings", MODE_PRIVATE)
        val newsModel: NewsModel? =NewsModel()
        val articles = newsModel?.articles
val t:Throwable=Throwable()
        showData(articles)
        Log.d("trace", "Error ${t.message}")


    }


    fun showData(articles: ArrayList<Article>?) {
        val adapter = NewsAdapter(this, articles)
        val recyclerView: RecyclerView = findViewById(R.id.rv)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.refresh_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        loadData()
        return super.onOptionsItemSelected(item)
    }

}