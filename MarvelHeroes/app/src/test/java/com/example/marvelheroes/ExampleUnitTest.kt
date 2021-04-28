package com.example.marvelheroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.carlosgub.coroutines.utils.CoroutinesRule
import com.carlosgub.coroutines.utils.getOrAwaitValue
import com.example.marvelheroes.core.interactor.Interactor
import com.example.marvelheroes.features.heroes.domain.interactor.GetCharactersInteractor
import com.example.marvelheroes.features.heroes.presentation.viewmodel.CharactersViewModel
import com.example.marvelheroes.features.heroes.presentation.viewmodel.state.CharactersVS
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = CoroutinesRule()

    private val useCaseMock = mockk<GetCharactersInteractor>()
    private lateinit var viewModel: CharactersViewModel
    private val observer = mockk<Observer<CharactersVS>>(relaxed = true)

    @Test
    fun `retrieve posts successful`() {
        //coEvery { useCaseMock.execute(Interactor.None) } returns TestData.dataList.asFlow()
        viewModel.getCharacters()
        viewModel.viewState.getOrAwaitValue()
        verify(exactly = 3) { observer.onChanged(any<CharactersVS.AddCharacter>()) }
    }
}