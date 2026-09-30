package com.shenawynkov.procoretest.ui.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shenawynkov.procoretest.domain.PokemonRepo
import com.shenawynkov.procoretest.domain.models.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UIState {
    object Loading : UIState

    @Immutable
    data class Success(val images: List<Image>) : UIState
    data class Failure(val msg: String) : UIState
}

class PekomonViewModel(val repo: PokemonRepo) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {

        getPoke()
    }

    fun getPoke() {
        viewModelScope.launch {
            repo.getPokemons().fold(
                onSuccess = { list ->
                    _uiState.update {
                        UIState.Success(list)
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        UIState.Failure(e.localizedMessage ?: "some error")

                    }
                })


        }
    }


}