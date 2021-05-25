package com.myproject.imagefiltersapp.repositories

import android.graphics.Bitmap
import android.net.Uri
import com.myproject.imagefiltersapp.data.ImageFilter

interface EditImageRepository {
    suspend fun prepareImagePreview(imageUri: Uri): Bitmap?
    suspend fun getImageFilters(image: Bitmap): List<ImageFilter>
    suspend fun saveFilteredImage(filteredBitmap: Bitmap): Uri?
}