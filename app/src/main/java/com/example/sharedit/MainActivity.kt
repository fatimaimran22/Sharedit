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
        val folderList= listOf(Folder(R.drawable.folder_icon,"Folder 1"),
            Folder(R.drawable.folder_icon,"Folder 2"),
            Folder(R.drawable.folder_icon,"Folder 3"),
            Folder(R.drawable.folder_icon,"Folder 4"),
            Folder(R.drawable.folder_icon,"Folder 5")
        )

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP  // ADD THIS LINE
        layoutManager.justifyContent = JustifyContent.FLEX_START

        myList.layoutManager = layoutManager
        myList.adapter = ItemAdapter(folderList)

    }
}