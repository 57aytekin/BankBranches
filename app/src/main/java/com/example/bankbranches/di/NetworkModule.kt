package com.example.bankbranches.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bankbranches.BuildConfig
import com.example.bankbranches.domain.services.BankService
import com.example.bankbranches.util.Constants.BANKSERVICE
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val TIMEOUT = 60L

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    @Named("chucker")
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context)
            .build()

    fun provideOkHttpClient(
        @Named("chucker") chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(chuckerInterceptor)
                }
            }
            .build()

    @Singleton
    @Provides
    fun provideapiRetrofit(
        @Named(BANKSERVICE)
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): BankService {

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .build()
            .create(BankService::class.java)
    }

    @Singleton
    @Provides
    @Named(BANKSERVICE)
    fun provideApiHttpClient(
        @Named("chucker") chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        return provideOkHttpClient(chuckerInterceptor)
    }

}