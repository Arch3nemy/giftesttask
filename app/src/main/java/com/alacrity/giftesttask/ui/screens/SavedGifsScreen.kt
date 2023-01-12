package com.alacrity.giftesttask.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alacrity.giftesttask.entity.Gif

@Composable
fun SavedGifsScreen(gifs: List<Gif>, onBack: () -> Unit, onRemoveGif: (Gif?) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var curGif = gifs.firstOrNull()

    if (showDialog) {
        AlertDialog(
            title = { },
            text = { Text(text = "Are you sure want to remove this gif?", color = Color.White) },
            backgroundColor = Color.DarkGray,
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = { onRemoveGif(curGif); showDialog = false }) {
                    Text(text = "Confirm", color = Color.White)
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Dismiss", color = Color.White)
                }
            },
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarSavedGifs(onBack = onBack)
        if (gifs.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
            ) {
                items(
                    items = gifs,
                    key = { it.images.original.url }
                ) { gif ->
                    GifView(gif = gif) {
                        curGif = gif
                        showDialog = true
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Saved gifs will appear here"
                )
            }
        }
    }



    BackHandler {
        onBack()
    }

}