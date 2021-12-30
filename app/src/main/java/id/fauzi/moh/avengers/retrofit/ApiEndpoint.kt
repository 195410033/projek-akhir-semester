package id.fauzi.moh.avengers.retrofit

import id.fauzi.moh.avengers.MainModel
import retrofit2.Call
import retrofit2.http.GET

// API (Application Programming Interface) merupakan sebuah interface yang dapat menghubungkan suatu aplikasi dengan aplikasi lainnya,
// baik dalam satu platform yang sama maupun berbeda.
interface ApiEndpoint {

    @GET("4e144cb1-0299-42dc-a1a3-34df9c1f1cac")
    fun getData(): Call<MainModel>

}