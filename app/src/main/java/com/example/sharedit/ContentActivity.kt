package com.example.sharedit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date

class ContentActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContentItemAdapter
    lateinit var search:SearchView
    private val itemList = mutableListOf<File>() // Create a mutable list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val folderId = intent.getIntExtra("FOLDER_ID", -1)

        recyclerView = findViewById(R.id.recyclerView) // ID from your XML
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ContentItemAdapter(itemList)
        recyclerView.adapter = adapter

        val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result->
            if(result.resultCode == RESULT_OK){
                val imageUri: Uri? = result.data?.data
                imageUri?.let{
                    val currentDate = Date()

                    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                    val timeFormat = SimpleDateFormat("HH:mm:ss")

                    val dateString = dateFormat.format(currentDate)
                    val timeString = timeFormat.format(currentDate)

                    val newItem = File(
                        pic = it.toString(),
                        name = "Name: ",
                        name_d = "Image",
                        date_d = dateString,
                        time_d = timeString,
                        date = "Date: ",
                        time = "Time: "
                    )

                    itemList.add(newItem)
                    adapter.notifyDataSetChanged()
                }

            }
        }

        fun saveBitmapToFile(bitmap: Bitmap): String {
            val filename = "IMG_${System.currentTimeMillis()}.jpg"
            val file = java.io.File(filesDir, filename)
            val outputStream = file.outputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            return file.absolutePath // return the path of the saved image
        }


        val captureImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Capture image result
                val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                imageBitmap?.let {
                    val imagePath = saveBitmapToFile(it) // <-- Save the bitmap and get file path

                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                    val timeFormat = SimpleDateFormat("HH:mm:ss")
                    val dateString = dateFormat.format(currentDate)
                    val timeString = timeFormat.format(currentDate)

                    val newItem = File(
                        pic = imagePath,   // <-- Store file path here
                        name = "Name: ",
                        name_d = "Image",
                        date_d = dateString,
                        time_d = timeString,
                        date = "Date: ",
                        time = "Time: "
                    )

                    itemList.add(newItem)
                    adapter.notifyDataSetChanged()
                }

            }
        }

        // File launcher
        val fileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val fileUri: Uri? = result.data?.data
                fileUri?.let {
                    val currentDate = Date()

                    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                    val timeFormat = SimpleDateFormat("HH:mm:ss")

                    val dateString = dateFormat.format(currentDate)
                    val timeString = timeFormat.format(currentDate)

                    val newItem = File(
                        pic = it.toString(), // Storing file Uri as string
                        name = "Name: ",
                        name_d = "File",
                        date_d = dateString,
                        time_d = timeString,
                        date = "Date: ",
                        time = "Time: "
                    )

                    itemList.add(newItem)
                    adapter.notifyDataSetChanged()
                }
            }
        }


        val imp_image = findViewById<Button>(R.id.imp_images)
        imp_image .setOnClickListener {
            val start = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            start.type = "image/*"
            imageLauncher.launch(start)
        }

        val capture_img = findViewById<Button>(R.id.capture_images)
        capture_img.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            captureImageLauncher.launch(cameraIntent)
        }

        val imp_file = findViewById<Button>(R.id.imp_files)
        imp_file.setOnClickListener {
            val start = Intent(Intent.ACTION_GET_CONTENT)
            start.type = "*/*" // allow any file type
            start.addCategory(Intent.CATEGORY_OPENABLE) // only show openable files
            fileLauncher.launch(start)
        }



    }




}
