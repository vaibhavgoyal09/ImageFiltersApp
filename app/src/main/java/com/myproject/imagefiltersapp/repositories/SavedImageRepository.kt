package com.myproject.imagefiltersapp.repositories

import android.graphics.Bitmap
import java.io.File

interface SavedImageRepository {

    suspend fun loadSavedImages(): List<Pair<File, Bitmap>>?
}