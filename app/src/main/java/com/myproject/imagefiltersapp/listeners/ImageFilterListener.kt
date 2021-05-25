package com.myproject.imagefiltersapp.listeners

import com.myproject.imagefiltersapp.data.ImageFilter

interface ImageFilterListener {
    fun onFilterSelected(imageFilter : ImageFilter)
}