package com.example.exampleoflistview

import android.graphics.Bitmap

data class Movie(val name: String, val imageUrl: String? = null) {
    var bitmap: Bitmap? = null
}
