package com.example.nammahomestay.di

import com.example.nammahomestay.data.repository.MockHomestayRepository
import com.example.nammahomestay.domain.repository.HomestayRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideHomestayRepository(): HomestayRepository {
        // Switch to FirebaseHomestayRepository(firestore) when google-services.json is added
        return MockHomestayRepository()
    }
}
