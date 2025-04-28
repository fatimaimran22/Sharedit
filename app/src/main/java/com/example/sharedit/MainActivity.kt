package com.example.sharedit

import android.os.Bundle
import android.widget.Button
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

class MainActivity : AppCompatActivity() {
    lateinit var addBtn:Button
    lateinit var myList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        addBtn=findViewById(R.id.addbtn)


        myList=findViewById(R.id.recyclerView)
        var folderList: MutableList<Folder> = mutableListOf()

        addBtn.setOnClickListener {
            // Create a new folder
            val newFolder = Folder(R.drawable.folder_icon, "Folder ${folderList.size + 1}")

            // Add it to the list
            folderList.add(newFolder)

            // Notify the adapter that the data has changed
            myList.adapter?.notifyItemInserted(folderList.size - 1)
        }

        folderList?.let {
            val layoutManager = FlexboxLayoutManager(this)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.justifyContent = JustifyContent.FLEX_START

            myList.layoutManager = layoutManager
            myList.adapter = ItemAdapter(it)
        }


    }
}