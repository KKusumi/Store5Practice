package com.example.store5practice.core.network.di

import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.DebugLogger
import com.example.store5practice.core.network.BuildConfig
import com.example.store5practice.core.network.NetworkDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
        )
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        networkJson: Json,
        okHttpCallFactory: Call.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .callFactory(okHttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()

// TODO 次回実装
//    @Provides
//    @Singleton
//    fun providesRetrofitNetworkApi(retrofit: Retrofit): RetrofitNetworkApi =
//        retrofit.create(RetrofitNetworkApi::class.java)

    @Provides
    @Singleton
    fun imageLoader(
        okHttpCallFactory: dagger.Lazy<Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = ImageLoader.Builder(application)
        .callFactory { okHttpCallFactory.get() }
        .components { add(SvgDecoder.Factory()) }
        .respectCacheHeaders(false)
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()
}

// TODO 次回実装
//@Module
//@InstallIn(SingletonComponent::class)
//internal interface NetworkBindModule {
//    @Binds
//    fun bindsNetworkDataSource(
//        retrofitNetworkDataSource: RetrofitNetworkDataSource,
//    ): NetworkDataSource
//}
