package kr.pe.ssun.composedemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kr.pe.ssun.composedemo.data.SearchRepository
import kr.pe.ssun.composedemo.data.SearchService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kr.pe.ssun.composedemo.BuildConfig as BuildConfig1

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Singleton
    @Provides
    fun providesHeaderInterceptor() = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", BuildConfig1.CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", BuildConfig1.CLIENT_SECRET)
            .build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/v1/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonPlaceHolderService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(searchService: SearchService): SearchRepository {
        return SearchRepository(searchService)
    }
}