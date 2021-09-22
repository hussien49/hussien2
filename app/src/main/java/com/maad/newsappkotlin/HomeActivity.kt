package com.maad.newsappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun openGeneralNews(view: View) {
        openActivity(/*"general"*/)
    }

    fun openTechnologyNews(view: View) {
       // openActivity("technology")
    }

    fun openSportsNews(/*view: View*/) {
       openActivity(/*"sports"*/)
    }

    fun openActivity(/*cat: String*/) {
        val intent = Intent(this, MainActivity::class.java)
    //    intent.putExtra("category", cat)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val exitDialog = ExitDialog()
        exitDialog.isCancelable = false
        exitDialog.show(supportFragmentManager, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.item_favorites -> {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}