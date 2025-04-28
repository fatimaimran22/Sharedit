package com.example.sharedit

import android.content.ClipData
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private var itemList: List<Folder>) : RecyclerView.Adapter<ItemViewHolder>() {

    private var filteredList = itemList // Start with the original list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_folder_style, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = filteredList[position]
        holder.folderPic.setImageResource(item.pic)
        holder.folderName.text = item.name

        val editText = holder.itemView.findViewById<EditText>(R.id.editText)

        holder.folderName.setOnClickListener {
            // Check if EditText already exists, and if not, create it
            if (editText.visibility == View.GONE) {
                holder.folderName.visibility = View.GONE
                editText.setText(item.name)
                editText.visibility = View.VISIBLE
                editText.requestFocus()
            }
        }

        // Handle focus loss to save the new name
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val newName = editText.text.toString()
                item.name = newName
                notifyItemChanged(position)

                // Hide the EditText and show the TextView again
                holder.folderName.text = newName
                holder.folderName.visibility = View.VISIBLE
                editText.visibility = View.GONE
            }
        }

        // Handle the Enter key press for saving the new name
        editText.setOnEditorActionListener { _, _, _ ->
            val newName = editText.text.toString()
            item.name = newName
            notifyItemChanged(position)

            // Hide the EditText and show the TextView again
            holder.folderName.text = newName
            holder.folderName.visibility = View.VISIBLE
            editText.visibility = View.GONE
            true
        }

        // Handle item click
        holder.itemView.setOnClickListener {
            // Create an intent to open ContentActivity
            val intent = Intent(holder.itemView.context, ContentActivity::class.java)

            // Optionally pass any data you need, like the folder ID or folder name
            intent.putExtra("FOLDER_ID", position)  // You can pass the position or any unique identifier
            holder.itemView.context.startActivity(intent)
        }
    }

    // Filter function that updates the list based on the search query
    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            itemList // No filter, return original list
        } else {
            itemList.filter { it.name.contains(query, ignoreCase = true) } // Filter the list
        }
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
