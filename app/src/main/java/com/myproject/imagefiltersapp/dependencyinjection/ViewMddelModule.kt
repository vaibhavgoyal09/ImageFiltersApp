package com.myproject.imagefiltersapp.dependencyinjection

import com.myproject.imagefiltersapp.viewmodels.EditImageViewModel
import com.myproject.imagefiltersapp.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditImageViewModel(editImageRepository = get()) }
    viewModel { SavedImagesViewModel(savedImageRepository = get()) }
}