package com.kinzlstanislav.hoslaviceo2.koin

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kinzlstanislav.hoslaviceo2.BuildConfig
import com.kinzlstanislav.hoslaviceo2.architecture.network.HoslaviceO2RestData
import com.kinzlstanislav.hoslaviceo2.architecture.repository.UsersRepository
import com.kinzlstanislav.hoslaviceo2.architecture.network.api.HoslaviceO2ApiService
import com.kinzlstanislav.hoslaviceo2.architecture.repository.database.AppDatabase
import com.kinzlstanislav.hoslaviceo2.architecture.repository.mapper.UsersMapper
import com.kinzlstanislav.hoslaviceo2.base.imageloading.GlideImageLoader
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    // view models
    factory { ListViewModel(get()) }

    // repository
    factory {
        UsersRepository(
            get(),
            get(),
            get()
        )
    }

    // mapper
    factory { UsersMapper() }

    // network
    factory {
        Retrofit.Builder()
            .baseUrl(HoslaviceO2RestData.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(
                OkHttpClient.Builder().apply {
                    if (BuildConfig.DEBUG) {
                        addNetworkInterceptor(
                            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                        )
                    }
                }.build()
            )
            .build()
            .create(HoslaviceO2ApiService::class.java)
    }

    // database
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app-database")
            .build()
    }

    // other
    single { GlideImageLoader() }
}
