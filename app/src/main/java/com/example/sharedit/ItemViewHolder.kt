package com.example.sharedit

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val folderPic: ImageView = itemView.findViewById(R.id.folderPic)
    val folderName: TextView = itemView.findViewById(R.id.folderName)
}
