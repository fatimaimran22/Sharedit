package com.example.sharedit

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList: List<Folder>):RecyclerView.Adapter<ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.single_folder_style, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int =itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.folderPic.setImageResource(item.pic)
        holder.folderName.text = item.name

        // Handle item click
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,"Item Clicked "+position, Toast.LENGTH_LONG).show()
        }
    }

}