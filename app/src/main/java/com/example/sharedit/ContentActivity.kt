package com.example.sharedit

import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class ContentActivity : AppCompatActivity() {
    lateinit var addBtn:Button
    lateinit var myList: RecyclerView
    lateinit var search:SearchView
    lateinit var adapter: ItemAdapter
    lateinit var folderList: MutableList<Folder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        addBtn=findViewById(R.id.addbtn)
        search=findViewById(R.id.searchView)
        myList=findViewById(R.id.recyclerView)

        folderList = mutableListOf()

        addBtn.setOnClickListener {
            // Create a new folder
            val newFolder = Folder(R.drawable.folder_icon, "Folder ${folderList.size + 1}")

            // Add it to the list
            folderList.add(newFolder)

            // Notify the adapter that the data has changed
            myList.adapter?.notifyItemInserted(folderList.size - 1)
        }

        adapter = ItemAdapter(folderList)

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.justifyContent = JustifyContent.FLEX_START

        myList.layoutManager = layoutManager
        myList.adapter = adapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })


    }
}