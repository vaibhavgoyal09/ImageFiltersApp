package com.myproject.imagefiltersapp.activities.savedimage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import com.myproject.imagefiltersapp.activities.editimage.EditImageActivity
import com.myproject.imagefiltersapp.activities.filteredimage.FilteredImageActivity
import com.myproject.imagefiltersapp.adapters.SavedImagesAdapter
import com.myproject.imagefiltersapp.databinding.ActivitySavedImageBinding
import com.myproject.imagefiltersapp.listeners.SavedImageListener
import com.myproject.imagefiltersapp.utilities.displayToast
import com.myproject.imagefiltersapp.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class SavedImageActivity : AppCompatActivity(), SavedImageListener {

    private lateinit var binding : ActivitySavedImageBinding
    private val viewModel: SavedImagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySavedImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setListeners()
        viewModel.loadSavedImages()
    }

    private fun setupObservers(){
        viewModel.savedImageUiState.observe(this, {
            val savedImageDataState= it ?: return@observe
            binding.savedImagesProgressBar.visibility=
                if(savedImageDataState.isLoading) View.VISIBLE else View.GONE
            savedImageDataState.savedImages?.let { savedImages ->
                SavedImagesAdapter(savedImages, this).also { adapter ->
                    with(binding.savedImagesRecyclerView){
                        this.adapter= adapter
                        visibility =View.VISIBLE
                    }
                }
            }?: run {
                savedImageDataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })
    }

    private fun setListeners(){
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onImageClicked(file: File) {
        val fileUri = FileProvider.getUriForFile(
            applicationContext,
            "${packageName}.provider",
            file
        )
        Intent(
            applicationContext,
            FilteredImageActivity::class.java
        ).also { filteredImageIntent ->
            filteredImageIntent.putExtra(EditImageActivity.KEY_FILTERED_IMAGE_URI, fileUri)
            startActivity(filteredImageIntent)
        }
    }
}