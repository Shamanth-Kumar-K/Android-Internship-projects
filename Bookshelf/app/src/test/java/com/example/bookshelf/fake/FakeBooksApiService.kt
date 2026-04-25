package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookSearchResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {
    override suspend fun getBooks(query: String): BookSearchResponse {
        return BookSearchResponse(items = listOf(FakeDataSource.book1, FakeDataSource.book2))
    }

    override suspend fun getBook(id: String): Book {
        return when (id) {
            FakeDataSource.book1.id -> FakeDataSource.book1
            FakeDataSource.book2.id -> FakeDataSource.book2
            else -> throw Exception("Book not found")
        }
    }
}

object FakeDataSource {
    val book1 = Book(
        id = "id1",
        volumeInfo = VolumeInfo(
            title = "Title 1",
            imageLinks = ImageLinks(thumbnail = "url1")
        )
    )
    val book2 = Book(
        id = "id2",
        volumeInfo = VolumeInfo(
            title = "Title 2",
            imageLinks = ImageLinks(thumbnail = "url2")
        )
    )
    val booksList = listOf(book1, book2)
}
