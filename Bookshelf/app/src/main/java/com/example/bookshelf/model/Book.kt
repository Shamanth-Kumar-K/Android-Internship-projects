package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String
)

data class BookSearchResponse(
    val items: List<Book>? = null
)
