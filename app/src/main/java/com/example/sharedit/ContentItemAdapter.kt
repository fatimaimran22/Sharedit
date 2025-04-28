package com.example.sharedit

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Date
import java.text.SimpleDateFormat

class ContentItemAdapter (private var itemList: List<File>):RecyclerView.Adapter<ItemViewHolder_Content>() {

    private var filteredList = itemList
    val currentDate = Date()
    var onItemLongClick: ((File) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder_Content {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_pic_style, parent, false)
        return ItemViewHolder_Content(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ItemViewHolder_Content, position: Int) {
        val item = filteredList[position]

        if (item.isImage) {
            // Use Glide to load the image from the URI or file path
            Glide.with(holder.itemView.context)
                .load(item.pic)  // item.pic should be a file path or URI
                .into(holder.folderPic)  // Assuming folderPic is an ImageView in your layout
        } else {
            // Show folder icon for non-image files (e.g., documents)
            holder.folderPic.setImageResource(R.drawable.folder_icon)  // Set the folder icon
        }

        holder.fileName.text = item.name
        holder.fileDate.text = item.date
        holder.fileTime.text = item.time
        holder.name_description.text = item.name_d
        holder.date_description.text = item.date_d
        holder.time_description.text = item.time_d

        if (item.isEditing) {
            holder.name_description.visibility = View.GONE
            holder.editText.visibility = View.VISIBLE
            holder.editText.setText(item.name_d)

            holder.editText.requestFocus()

            holder.editText.post {
                val imm = holder.itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(holder.editText, InputMethodManager.SHOW_IMPLICIT)
            }
        } else {
            holder.name_description.visibility = View.VISIBLE
            holder.editText.visibility = View.GONE
        }


        holder.name_description.setOnClickListener {
            item.isEditing = true
            notifyItemChanged(position)
        }

        holder.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                item.name_d = holder.editText.text.toString()
                item.isEditing = false
                notifyItemChanged(position)

                // Hide keyboard
                val imm = holder.itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(holder.editText.windowToken, 0)
                true
            } else {
                false
            }
        }

        holder.editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && item.isEditing) {
                item.name_d = holder.editText.text.toString()
                item.isEditing = false
                notifyItemChanged(position)
            }
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(item)
            true
        }
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            itemList // No filter, return original list
        } else {
            itemList.filter { it.name_d.contains(query, ignoreCase = true) } // Filter the list
        }
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

}