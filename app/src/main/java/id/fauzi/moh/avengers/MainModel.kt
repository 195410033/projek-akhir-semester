package id.fauzi.moh.avengers

// Data Classes merupakan kelas sederhana yang bisa berperan sebagai data container pada kotlin.
// Di dalamnya terdapat beberapa fungsi yang sudah disediakan untuk menghandle beberapa operasi data seperti equals(), toString(), hashCode(), & copy().

data class MainModel(val result: ArrayList<Result>) {
    data class Result(val id: Int, val title: String, val image: String)
}