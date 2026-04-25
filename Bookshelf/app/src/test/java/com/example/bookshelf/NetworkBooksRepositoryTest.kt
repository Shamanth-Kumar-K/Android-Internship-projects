package com.example.bookshelf

import com.example.bookshelf.data.DefaultBooksRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBooksList() = runTest {
        val repository = DefaultBooksRepository(
            booksApiService = FakeBooksApiService()
        )
        assertEquals(FakeDataSource.booksList, repository.getBooks("jazz history"))
    }

    @Test
    fun networkBooksRepository_getBook_verifyBook() = runTest {
        val repository = DefaultBooksRepository(
            booksApiService = FakeBooksApiService()
        )
        assertEquals(FakeDataSource.book1, repository.getBook("id1"))
    }
}
