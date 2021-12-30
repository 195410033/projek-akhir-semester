package id.fauzi.moh.avengers.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Retrofit adalah library di pemograman android.
// fungsi utama Retrofit adalah untuk meng-handle semua hal yang berkaitan dengan koneksi data dari android ke internet.
//
// contohnya :
//  - Merequest data ke server
//  - Menghandle apa yang terjadi saat koneksi timeout
//  - Mengintercep data sebelum di proses android
// kelebihan utama dari retrofit adalah mudahnya digabung dengan library lain dan bisa di kostumisasi sesuai kebutuhan,sehingga bisa retrofit sangat membantu untuk mempercepat pembuatan project.
// Jadi fungsi source code di bawah untuk mengambil data dari internet

object ApiService {

    val BASE_URL: String = "https://run.mocky.io/v3/"
    val endpoint: ApiEndpoint
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiEndpoint::class.java)
        }
}