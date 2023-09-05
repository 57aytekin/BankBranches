package com.example.bankbranches.di

import android.content.Context
import com.example.bankbranches.util.firebase.FirebaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseAnalyticsModule {
    @Provides
    @Singleton
    fun provideFirebaseAnalyticsModule(context: Context) = FirebaseProvider(context)
}