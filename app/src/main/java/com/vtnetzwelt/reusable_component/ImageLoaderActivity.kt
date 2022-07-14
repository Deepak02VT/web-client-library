package com.vtnetzwelt.reusable_component

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.vtnetzwelt.reusable_component.databinding.ActivityMainBinding
import com.vtnra.image.load
import java.io.File

class ImageLoaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewForDrawable.load(R.drawable.default_user_image)
        binding.imageViewForUrl.load(
            "https://cdn.shopify.com/s/files/1/0592/0342/0346/products/1.BesanLaddu.jpg?v=1629655689",
            R.drawable.default_user_image,
            R.drawable.error_image,
            ImageView.ScaleType.MATRIX,
            false,
            binding.progressBar
        )
        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    imageUri = intent?.data
                    imageUri?.let { binding.imageViewForUri.load(it) }
                    binding.imageViewForFile.load(File(getPath(imageUri)))
                }
            }

        binding.imageViewForFile.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startForResult.launch(gallery)
        }
    }

    private fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri!!, projection, null, null, null) ?: return null
        val index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s = cursor.getString(index)
        cursor.close()
        return s
    }
}