package com.example.sharedit

import android.content.ClipData
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Date
import java.text.SimpleDateFormat

class ContentItemAdapter (private var itemList: List<File>):RecyclerView.Adapter<ItemViewHolder_Content>() {

    private var filteredList = itemList
    val currentDate = Date()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder_Content {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_pic_style, parent, false)
        return ItemViewHolder_Content(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ItemViewHolder_Content, position: Int) {
        val item = filteredList[position]
        if (item.pic.isNotEmpty()) {
            // Use Glide to load the image from the URI or file path (String)
            Glide.with(holder.itemView.context)
                .load(item.pic)  // item.pic is a String (URI or file path)
                .into(holder.folderPic)  // Assuming folderPic is an ImageView
        }
        holder.fileName.text = item.name
        holder.fileDate.text = item.date.toString()
        holder.fileTime.text = item.time.toString()
        holder.name_description.text = item.name_d
        holder.date_description.text = item.date_d.toString()
        holder.time_description.text = item.time_d.toString()

    }

}