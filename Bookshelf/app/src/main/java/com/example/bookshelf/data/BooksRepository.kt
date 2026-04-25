package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>?
    suspend fun getBook(id: String): Book
}

class DefaultBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): List<Book>? {
        return booksApiService.getBooks(query).items
    }

    override suspend fun getBook(id: String): Book {
        return booksApiService.getBook(id)
    }
}
