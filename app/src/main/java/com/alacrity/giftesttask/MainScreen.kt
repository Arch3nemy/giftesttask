package com.alacrity.giftesttask

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.paging.compose.collectAsLazyPagingItems
import com.alacrity.giftesttask.ui.MainViewModel
import com.alacrity.giftesttask.ui.models.*
import com.alacrity.giftesttask.ui.screens.FirstScreen
import com.alacrity.giftesttask.ui.screens.SavedGifsScreen
import com.alacrity.giftesttask.ui.screens.SecondScreen
import com.alacrity.giftesttask.ui.view_states.MainViewState
import com.alacrity.giftesttask.util.toast

@Composable
fun MainScreen(
    context: Context,
    viewModel: MainViewModel,
) {

    val state by viewModel.viewState.collectAsState()
    val gifs = viewModel.bookPager.collectAsLazyPagingItems()
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.DarkGray,
                onClick = { viewModel.enterSavedScreen() }
            ) {
                Icon(Icons.Filled.DownloadDone, "")
            }
        }, content = { padding ->
            when (state) {
                MainViewState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator()
                    }
                }
                is MainViewState.FirstScreen -> {
                    FirstScreen(
                        gifs = gifs,
                        query = viewModel.query.value,
                        onClearQuery = {
                            viewModel.setQuery("")
                            viewModel.invalidateDataSource()
                        },
                        onQueryChanged = { viewModel.setQuery(it) },
                        onSearch = {
                            viewModel.invalidateDataSource()
                            focusManager.clearFocus()
                        },
                        onClick = { viewModel.enterSecondScreen(gifs.itemSnapshotList.indexOf(it)) }
                    )
                }
                is MainViewState.Error -> {
                    /* ShowErrorView */
                }
                is MainViewState.SecondScreen -> {
                    SecondScreen(
                        gifs,
                        (state as MainViewState.SecondScreen).currentGifIndex,
                        onBackClick = { viewModel.onBackClick() },
                        onSaveClick = { viewModel.onSaveClick(it); context.toast("Gif saved") },
                        onRemoveClick = { viewModel.onRemoveClick(it); context.toast("Gif removed") })
                }
                is MainViewState.SavedGifs -> {
                    SavedGifsScreen(
                        gifs = (state as MainViewState.SavedGifs).gifs,
                        onBack = { viewModel.onBackClick() },
                        onRemoveGif = { viewModel.onRemoveClick(it); context.toast("Gif removed") }
                    )
                }
            }

        })

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

}
