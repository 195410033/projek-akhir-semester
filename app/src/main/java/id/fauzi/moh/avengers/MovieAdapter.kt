package id.fauzi.moh.avengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_movie.view.*

// Adapter adalah objek dari kelas yang mengimplementasikan Adapter interface.
// Ini bertindak sebagai penghubung antara kumpulan data dan tampilan adaptor, objek dari kelas yang memperluas kelas AdapterView abstrak.
// Data set mampu menyajikan data apa saja secara terstruktur.
class MovieAdapter(var results: ArrayList<MainModel.Result>, val listener: OnAdapterListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // Setiap kali kita melakukan scroll pada RecyclerView, ia akan memeriksa memori apakah item view yang hendak ditampilkan tertentu sudah berada di memori  atau belum. Jika belum, maka akan dijalankan sebuah proses yang cukup mahal dari segi memori, yaitu dijalankannya onCreateViewHolder(). Di dalam metode ini, terjadi sebuah casting view (findViewById) yang akan menampilkan koleksi data dalam format tampilan yang ditentukan, baris per baris jika pada bentuk list atau baris dan kolom pada bentuk grid.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.textView.text = result.title

        // Glide adalah sumber terbuka perpustakaan Android yang populer untuk memuat gambar, video, dan GIF animasi.
        // Dengan Glide, pengguna dapat memuat dan menampilkan media dari berbagai sumber, seperti server jarak jauh atau sistem file lokal.
        // Glide digunakan untuk memuat sebuah gambar, baik yang sudah Anda siapkan di drawable maupun berada di server. Glide terdiri dari banyak fungsi:
        //  - with: digunakan untuk memasukkan context.
        //  - load: digunakan untuk memasukkan sumber gambar, contohnya menggunakan url atau asset dari Drawable.
        //  - apply: digunakan untuk melakukan memanipulasi gambar, contohnya menggunakan RequestOption untuk me-override ukuran gambar.
        //  - into: digunakan untuk memasukkan imageView sebagai obyek penampil gambar.
        Glide.with(holder.view)
            .load(result.image)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(holder.view.imageView)

        // Metode ini dipanggil bila pengguna menyentuh item (saat dalam mode sentuh). atau berfokus pada item dengan tombol navigasi atau trackball,
        // lalu menekan tombol "enter" yang sesuai atau menekan trackball.
        holder.view.imageView.setOnClickListener { listener.onClick(result) }
    }

    fun setData(data: List<MainModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }

    override fun getItemCount() = results.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}