package com.myproject.imagefiltersapp.dependencyinjection

import com.myproject.imagefiltersapp.repositories.EditImageRepository
import com.myproject.imagefiltersapp.repositories.EditImageRepositoryImpl
import com.myproject.imagefiltersapp.repositories.SavedImageRepository
import com.myproject.imagefiltersapp.repositories.SavedImagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext())}
    factory<SavedImageRepository>{ SavedImagesRepositoryImpl(androidContext()) }
}