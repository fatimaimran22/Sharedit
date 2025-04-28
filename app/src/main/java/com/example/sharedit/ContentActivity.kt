package com.example.sharedit

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date

class ContentActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContentItemAdapter
    private val itemList = mutableListOf<File>() // Create a mutable list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val folderId = intent.getIntExtra("FOLDER_ID", -1)

        recyclerView = findViewById(R.id.recyclerView) // ID from your XML
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ContentItemAdapter(itemList)
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.addbtn) // You need a Button in XML
        addButton.setOnClickListener {
            addNewItem()
        }
    }

    private fun addNewItem() {
        val currentDate = Date()

        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val timeFormat = SimpleDateFormat("HH:mm:ss")

        val dateString = dateFormat.format(currentDate)
        val timeString = timeFormat.format(currentDate)

        val newItem = File(
            pic = R.drawable.image, // Replace with actual image
            name = "Name: ",
            name_d = "Image",
            date_d = dateString,
            time_d = timeString,
            date = "Date: ",
            time = "Time: "
        )

        itemList.add(newItem)
        adapter.notifyDataSetChanged() // Update RecyclerView
    }
}
