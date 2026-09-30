package com.shenawynkov.procoretest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.shenawynkov.procoretest.ui.viewmodel.PekomonViewModel
import com.shenawynkov.procoretest.ui.viewmodel.UIState

@Composable
fun PokemonImageGridScreen(
    pekomonViewModel: PekomonViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by pekomonViewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UIState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.images) { image ->
                    AsyncImage(
                        model = image.url,
                        contentDescription = "Pokemon Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        is UIState.Loading -> { /* loading indicator */ }
        is UIState.Failure -> { /* error message */ }
    }
}
