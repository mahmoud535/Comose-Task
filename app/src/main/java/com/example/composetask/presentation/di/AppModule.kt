package com.example.composetask.presentation.di

import android.app.Application
import com.example.composetask.common.domain.AuthInterceptor
import com.example.composetask.data.remote.ApiService
import com.example.composetask.data.repository.RepositoryImp
import com.example.composetask.domain.repository.Repository
import com.example.composetask.domain.usecase.AddProductUseCase
import com.example.composetask.domain.usecase.AddUserUseCase
import com.example.composetask.domain.usecase.GetHomeItemListUseCase
import com.example.composetask.domain.usecase.GetOpenItemListUseCase
import com.example.composetask.domain.usecase.GetServiceItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BaseUrl = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideAuthInterceptor(application: Application): AuthInterceptor {
        return AuthInterceptor(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun getRetroBuilder(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideServiceRepository(apiService: ApiService): Repository {
        return RepositoryImp(apiService)
    }

    @Provides
    fun provideGetServiceItemsUseCase(repository: Repository): GetServiceItemsUseCase {
        return GetServiceItemsUseCase(repository)
    }

    @Provides
    fun provideGetOpenListItemsUseCase(repository: Repository): GetOpenItemListUseCase {
        return GetOpenItemListUseCase(repository)
    }

    @Provides
    fun provideGetHomeListItemsUseCase(repository: Repository): GetHomeItemListUseCase {
        return GetHomeItemListUseCase(repository)
    }
    @Provides
    fun provideAddUserUseCase(userRepository: Repository): AddUserUseCase {
        return AddUserUseCase(userRepository)
    }
    @Provides
    fun provideAddProductUseCase(userRepository: Repository): AddProductUseCase {
        return AddProductUseCase(userRepository)
    }
}
