package com.example.sharedit

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder_Content(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val folderPic: ImageView = itemView.findViewById(R.id.img)
    val fileName: TextView = itemView.findViewById(R.id.Name)
    val fileDate: TextView = itemView.findViewById(R.id.date)
    val fileTime: TextView = itemView.findViewById(R.id.time)
    val name_description: TextView = itemView.findViewById(R.id.name_description)
    val date_description: TextView = itemView.findViewById(R.id.date_description)
    val time_description: TextView = itemView.findViewById(R.id.time_description)

}
