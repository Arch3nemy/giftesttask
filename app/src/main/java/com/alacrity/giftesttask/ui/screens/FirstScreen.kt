package com.alacrity.giftesttask.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.alacrity.giftesttask.entity.Gif
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    gifs: LazyPagingItems<Gif>,
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onClearQuery: () -> Unit,
    onClick: (Gif?) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {

        SearchView(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChanged = onQueryChanged,
            onSearch = onSearch,
            onClearQuery = onClearQuery
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                )
        ) {
            items(
                items = gifs,
                key = { it.images.original.url }
            ) { gif ->
                if (gif != null) {
                    GifView(gif = gif) {
                        onClick(gif)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Search for awesome Gifs!",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }


    }

}

@Composable
fun GifView(modifier: Modifier = Modifier, gif: Gif?, onClick: () -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(500.dp)
        .clickable {
            onClick()
        }) {
        GlideImage(
            imageModel = { gif?.images?.original?.url },
            loading = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        )
    }
}