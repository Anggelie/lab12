package com.uvg.lab12anggelie


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.reflect.KProperty

private val showProgresss: Any
    get() {
        TODO("Not yet implemented")
    }
private val errorMessage: Any
    get() {
        TODO("Not yet implemented")
    }

@Composable
fun CharacterDetailRoute(
    characterId: Int,
    navigateBack: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = CharacterDetailViewModel.Factory)
) {

    LaunchedEffect(characterId) {
        viewModel.loadCharacterDetails(characterId)
    }

    CharacterDetailsScreen(
        navigateBack = navigateBack
    )
}

private operator fun Unit.getValue(nothing: Nothing?, property: KProperty<*>): Any {
    TODO("Not yet implemented")
}

private fun <T> collectAsStateWithLifecycle(): T {
    TODO("Not yet implemented")
}

@Composable
fun CharacterDetailsScreen(
    navigateBack: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        CharacterAppBar(onNavigateBack = navigateBack)
        Spacer(modifier = Modifier.height(16.dp))
        when {
            showProgresss as Boolean -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterAppBar(onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { Text("Character Info") },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Go back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )
}

@Composable
fun AsyncImage() {
    TODO("Not yet implemented")
}

