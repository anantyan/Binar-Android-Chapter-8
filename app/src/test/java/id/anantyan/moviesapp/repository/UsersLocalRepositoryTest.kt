package id.anantyan.moviesapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import id.anantyan.moviesapp.data.local.UsersDao
import id.anantyan.moviesapp.data.local.model.UsersLocal
import id.anantyan.moviesapp.utils.DataStoreManager
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class UsersLocalRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var dao: UsersDao
    private lateinit var store: DataStoreManager
    private lateinit var repo: UsersLocalRepository

    @Test
    fun getAccount() = runBlocking {
        val correct = mockk<UsersLocal>()
        Mockito.`when`(dao.showAccount(any())).thenReturn(correct)
        val response = repo.getAccount()
        assertEquals(response, correct)
    }

    @Test
    fun login() = runBlocking {
        val correct = mockk<UsersLocal>()
        Mockito.`when`(dao.login("abc@mail.com", "123")).thenReturn(correct)
        val response = repo.login("abc@mail.com", "123")
        assertEquals(response, correct)
    }

    @Test
    fun checkAccountByEmail() = runBlocking {
        val correct = mockk<UsersLocal>()
        Mockito.`when`(dao.checkAccount(null, "abc@mail.com")).thenReturn(correct)
        val response = repo.checkAccountByEmail("abc@mail.com")
        assertEquals(response, correct)
    }

    @Before
    fun setUp() {
        dao = mock()
        store = mock()
        repo = UsersLocalRepository(dao, store)
    }
}