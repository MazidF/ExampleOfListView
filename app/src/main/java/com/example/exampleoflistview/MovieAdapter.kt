package com.example.exampleoflistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.exampleoflistview.databinding.MovieLayoutBinding

class MovieAdapter(context: Context, movies: List<Movie>) :
    ArrayAdapter<Movie>(context, 0, movies) {

    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val result: View
        val holder: MyHolder
        val movie = getItem(position)!!

        if (convertView == null) {
            result = inflater.inflate(R.layout.movie_layout, parent, false)
            holder = MyHolder(result)
            result.tag = holder
        } else {
            result = convertView
            holder = result.tag as MyHolder
        }

        holder.fill(movie)
        return result
    }

    class MyHolder(view: View) {
        private val binding: MovieLayoutBinding = DataBindingUtil.bind(view)!!

        fun fill(movie: Movie) {
            binding.movie = movie
        }
    }

}


object MyBaseAdapter : BaseAdapter() {
    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}