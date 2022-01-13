package com.example.apprenticeassignment2.services

import com.example.apprenticeassignment2.data.PhotosResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "1508443e49213ff84d566777dc211f2a"

//Required as query params by flicker api
const val API_METHOD = "flickr.photos.search"
const val API_RESPONSE_FORMAT = "json"
const val API_RESPONSE_CALLBACK = "1"

//Perform get request from API

interface FlickrApiService {
    //Correct url to fetch photos is
    //https://api.flickr.com/services/rest?format=json&nojsoncallback=1&method=flickr.photos.search&api_key=1508443e49213ff84d566777dc211f2a&page=1&tags=eagle&per_page=30
    //So we need,
    // format (required)
    // nojsoncallback(required)
    // method(required)
    // api_key(required)
    // page(optional but required in our case for pagination)
    // tags(optional but required in our case)
    // per_page(optional but required in our case for pagination)
    @GET("rest")
    fun getImage(
        @Query("format") format: String = API_RESPONSE_FORMAT,
        @Query("nojsoncallback") jsonCallback: String = API_RESPONSE_CALLBACK,
        @Query("method") method: String = API_METHOD,
        @Query("api_key") key: String = API_KEY,
        @Query("page") nextPage: Int,
        @Query("tags") input: String,
        @Query("per_page") itemsPerPage: Int
    ): Deferred<Response<PhotosResponse>>


    companion object {
        private const val BASE_URL = "https://api.flickr.com/services/"
        fun getApi(): FlickrApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(FlickrApiService::class.java)
        }
    }
}