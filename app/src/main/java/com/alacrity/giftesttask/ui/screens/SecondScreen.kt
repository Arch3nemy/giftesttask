package com.alacrity.giftesttask.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.paging.compose.LazyPagingItems
import com.alacrity.giftesttask.entity.Gif
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SecondScreen(
    gifs: LazyPagingItems<Gif>,
    index: Int,
    onBackClick: () -> Unit,
    onSaveClick: (Gif?) -> Unit,
    onRemoveClick: (Gif?) -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = gifs.itemCount,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = index,
    )
    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            onBack = onBackClick,
            onSave = { onSaveClick(gifs.itemSnapshotList[index]) },
            onRemove = { onRemoveClick(gifs.itemSnapshotList[index]) }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { gifs[index]?.images?.original?.url },
                    imageOptions = ImageOptions(contentScale = ContentScale.Fit),
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                )
            }
        }

        BackHandler {
            onBackClick()
        }

    }
}