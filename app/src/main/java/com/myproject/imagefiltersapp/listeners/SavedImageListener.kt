package com.myproject.imagefiltersapp.listeners

import java.io.File

interface SavedImageListener {
    fun onImageClicked(file: File)
}