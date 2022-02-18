package com.example.exampleoflistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.exampleoflistview.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val client = OkHttpClient()
    lateinit var list: ArrayList<Movie>
    var imageUrls = arrayOfNulls<ImageItem>(20)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getJson()
        setAdapter()
    }

    private fun getJson() {
        val request = Request.Builder()
            .url("https://picsum.photos/v2/list?limit=20")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@MainActivity, "We have a problem!!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                if (body != null) {
                    val listType = object : TypeToken<Array<ImageItem?>?>() {}.type
                    val gson = GsonBuilder().create()
                    imageUrls = gson.fromJson(body, listType)
                    setAdapter()
                }
            }
        })
    }

    private fun setAdapter() {
        list = arrayListOf<Movie>().apply {
            repeat(20) {
                add(Movie("Movie${it + 1}", imageUrls[it]?.download_url))
            }
        }

        val movieAdapter = MovieAdapter(this, list)
        runOnUiThread {
            binding.list.apply {
                adapter = movieAdapter
            }
        }
    }
}
