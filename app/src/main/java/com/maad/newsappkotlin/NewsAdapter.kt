package com.maad.newsappkotlin

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val activity: Activity, val articles: ArrayList<Article>?) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv)
        val image: ImageView = view.findViewById(R.id.iv)
        val parent: CardView = view.findViewById(R.id.card_view)
        val favorites: ImageView = view.findViewById(R.id.iv_favorite)
        val description: TextView = view.findViewById(R.id.description)
        val score: TextView = view.findViewById(R.id.Score)
        val name: TextView = view.findViewById(R.id.name)
        val episode : TextView = view.findViewById(R.id.episode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val view = activity.layoutInflater.inflate(R.layout.news_list_item, parent, false)
        val holder = NewsViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.name.setText(articles?.get(position)?.name)
       // holder.score.setText(articles?.get(position)?.name)
        holder.description.setText(articles?.get(position)?.description)
        holder.episode.setText(articles?.get(position)?.episodes)
        Glide.with(activity).load(articles?.get(position)?.url).into(holder.image)
        holder.parent.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articles?.get(position)?.url))
            activity.startActivity(intent)
        }
        holder.favorites.setOnClickListener {
            val helper = DBHelper(activity)
            val db = helper.writableDatabase
            val values = ContentValues()
            values.put("title", articles?.get(position)?.description)
            values.put("url", articles?.get(position)?.url)
            val rowID = db.insert("Favorites", null, values)
            if (rowID != -1L)
                Toast.makeText(activity, "Added to favorites", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity, "Item Already Exists in favorites", Toast.LENGTH_SHORT)
                    .show()
        }//  that for the favourites
    }




    override fun getItemCount() = articles?.size ?: 0

}