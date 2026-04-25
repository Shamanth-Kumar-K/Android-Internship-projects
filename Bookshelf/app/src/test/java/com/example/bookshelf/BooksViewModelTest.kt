package com.example.bookshelf

import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.data.DefaultBooksRepository
import com.example.bookshelf.ui.screens.BooksUiState
import com.example.bookshelf.ui.screens.BooksViewModel
import com.example.bookshelf.rules.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BooksViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun booksViewModel_getBooks_verifyBooksUiStateSuccess() = runTest {
        val booksViewModel = BooksViewModel(
            booksRepository = DefaultBooksRepository(FakeBooksApiService())
        )
        assertEquals(
            BooksUiState.Success(FakeDataSource.booksList),
            booksViewModel.booksUiState
        )
    }
}
