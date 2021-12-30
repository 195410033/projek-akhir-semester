package id.fauzi.moh.avengers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import id.fauzi.moh.avengers.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = "Avengers"
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // RecyclerView adalah tampilan komponen (widget) yang lebih fleksibel, juga memiliki kemampuan untuk menampilkan data dalam jumlah besar secara efisien.
    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(arrayListOf(), object : MovieAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
                Toast.makeText(
                    applicationContext, result.title,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = movieAdapter
        }
    }

    // mengambil data dari internet yang telah disimpan ke dalam data JSON
    private fun getDataFromApi() {
        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel> {
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        showData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    printLog("onFailure: $t")
                }
            })
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    // menampilkan pesan logcat
    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    // menampilkan gambar di layout
    private fun showData(data: MainModel) {
        val results = data.result
        movieAdapter.setData(results)
    }
}