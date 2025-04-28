package com.example.sharedit

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
            Toast.makeText(holder.itemView.context,"Item Clicked "+position, Toast.LENGTH_LONG).show()
        }
    }

}