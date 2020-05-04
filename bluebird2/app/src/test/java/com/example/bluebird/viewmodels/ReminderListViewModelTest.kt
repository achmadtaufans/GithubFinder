package com.example.bluebird.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bluebird.api.ApiMain
import com.example.bluebird.data.ReminderListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response


@RunWith(JUnit4::class)
class MyViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var apiResponse: Response<ReminderListResponse>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `get speed data from API` () = runBlocking {
        // call the api
        val api = ApiMain().services
        apiResponse = api.getReminder("5e7988f52d00005cbf18bd7b").execute()
        // verify the response is OK
        assertEquals("ReminderListResponse(todos=[ReminderList(id=1), ReminderList(id=2), ReminderList(id=2)])", apiResponse.body().toString())
        return@runBlocking
    }
}
