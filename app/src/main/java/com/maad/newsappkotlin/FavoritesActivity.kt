package com.maad.newsappkotlin

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val helper = DBHelper(this)
        val db = helper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Favorites", null)

        val titles = arrayListOf<String>()
        val urls = arrayListOf<String>()

        while (cursor.moveToNext()) {
            titles.add(cursor.getString(0))
            urls.add(cursor.getString(1))
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        val list: ListView = findViewById(R.id.lv)
        list.adapter = adapter

        list.setOnItemLongClickListener { parent, view, position, id ->
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            //label is there only for developers, not for app user
            val clip = ClipData.newPlainText("N/A", urls.get(position))
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Title Copied to Clipboard", Toast.LENGTH_SHORT).show()
            true
        }

    }
}