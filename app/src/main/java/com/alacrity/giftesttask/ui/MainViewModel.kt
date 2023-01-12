package com.alacrity.giftesttask.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alacrity.giftesttask.di.base.ViewModelKey
import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.repository.Repository
import com.alacrity.giftesttask.ui.models.MainEvent
import com.alacrity.giftesttask.ui.paging.GifPagingSource
import com.alacrity.giftesttask.ui.paging.GifPagingSource.Companion.LIMIT
import com.alacrity.giftesttask.ui.view_states.MainViewState
import com.alacrity.giftesttask.ui.view_states.MainViewState.*
import com.alacrity.giftesttask.use_cases.GetGifsFromDBUseCase
import com.alacrity.giftesttask.use_cases.RemoveGifFromDatabaseUseCase
import com.alacrity.giftesttask.use_cases.SaveGifToDatabaseUseCase
import com.alacrity.giftesttask.util.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val saveGifToDatabaseUseCase: SaveGifToDatabaseUseCase,
    private val getGifsFromDBUseCase: GetGifsFromDBUseCase,
    private val removeGifFromDatabaseUseCase: RemoveGifFromDatabaseUseCase,
    private val repository: Repository,
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    var query = mutableStateOf("cats")
    private lateinit var pagingSource: GifPagingSource

    val bookPager =
        Pager(PagingConfig(pageSize = LIMIT, initialLoadSize = LIMIT, enablePlaceholders = false)) {
            GifPagingSource(query.value, repository).also { pagingSource = it }
        }.flow.cachedIn(viewModelScope)


    val viewState: StateFlow<MainViewState> = _viewState
    private val savedGifs = mutableListOf<Gif>()

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FirstScreen -> currentState.reduce(event)
            is SecondScreen -> currentState.reduce(event)
            is SavedGifs -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                loadGifsFromDB()
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)
    }

    private fun FirstScreen.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.EnterSecondScreen -> {
                _viewState.value = SecondScreen(event.index)
            }
            is MainEvent.EnterSavedGifs -> {
                _viewState.value = SavedGifs(savedGifs)
            }
            else -> Unit
        }
    }

    private fun SavedGifs.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.OnBackClicked -> {
                _viewState.value = FirstScreen
            }
            is MainEvent.OnRemoveClicked -> {
                launch(
                    logSuccess = "Successfully removed gif",
                    logError = "Error removing gif",
                    onSuccess = { event.gif?.let { savedGifs.remove(it) } },
                    onFailure = { },
                ) {
                    event.gif?.let { removeGifFromDatabaseUseCase(it) }
                }
            }
            else -> Unit
        }
    }

    private fun loadGifsFromDB() {
        _viewState.value = FirstScreen
        launch(
            logError = "Error Getting Gifs",
            logSuccess = "Successfully received gifs list",
            onSuccess = {
                savedGifs.addAll(it)
            },
            onFailure = { e ->
                _viewState.value = Error(e)
            }
        ) {
            getGifsFromDBUseCase.invoke()
        }
    }

    private fun SecondScreen.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.OnSaveClicked -> {
                launch(
                    logSuccess = "Successfully saved gif",
                    logError = "Error saving gif",
                    onSuccess = { event.gif?.let { savedGifs.add(it) } },
                    onFailure = { },
                ) {
                    event.gif?.let { saveGifToDatabaseUseCase(item = it) }
                }
            }
            is MainEvent.OnBackClicked -> {
                _viewState.value = FirstScreen
            }
            is MainEvent.OnRemoveClicked -> {
                launch(
                    logSuccess = "Successfully removed gif",
                    logError = "Error removing gif",
                    onSuccess = { event.gif?.let { savedGifs.remove(it) } },
                    onFailure = { },
                ) {
                    event.gif?.let { removeGifFromDatabaseUseCase(it) }
                }
            }
            is MainEvent.EnterSavedGifs -> {
                _viewState.value = SavedGifs(savedGifs)
            }
            else -> Unit
        }
    }

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun invalidateDataSource() {
        pagingSource.invalidate()
    }
}