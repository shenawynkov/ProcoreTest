package com.shenawynkov.procoretest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.shenawynkov.procoretest.data.repo.PokemonRepoImpl
import com.shenawynkov.procoretest.networking.APiProvider
import com.shenawynkov.procoretest.ui.PokemonImageGridScreen
import com.shenawynkov.procoretest.ui.theme.ProcoreTestTheme
import com.shenawynkov.procoretest.ui.viewmodel.PekomonViewModel


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<PekomonViewModel> {
        viewModelFactory {
            initializer {
                PekomonViewModel(PokemonRepoImpl(APiProvider.apiService))
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ProcoreTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonImageGridScreen(viewModel)
                }
            }
        }
    }
}

