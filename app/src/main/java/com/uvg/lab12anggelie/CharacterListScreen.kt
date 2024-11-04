package com.uvg.lab12anggelie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharacterListRoute(
    onCharacterSelected: (Int) -> Unit,
    viewModel: CharacterCollectionViewModel = viewModel(factory = CharacterCollectionViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val characters by viewModel.characters.collectAsStateWithLifecycle()
    CharacterListScreen(
        state = state,
        characters = characters,
        onCharacterSelected = onCharacterSelected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    state: CharacterCollecionUIState,
    characters: List<Character>,
    onCharacterSelected: (Int) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        CharacterListAppBar()
        when {
            state.showProgress -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.errorMessage != null -> {
                Text(
                    text = "Error: ${state.errorMessage}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {
                CharacterList(characters = characters, onCharacterSelected = onCharacterSelected)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterListAppBar() {
    TopAppBar(
        title = { Text("Character Gallery") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun CharacterList(characters: List<Character>, onCharacterSelected: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        items(characters) { character ->
            CharacterListItem(character = character, onCharacterSelected = onCharacterSelected)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CharacterListItem(character: Character, onCharacterSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCharacterSelected(character.id) }
            .padding(8.dp)
    ) {
        AsyncImage()
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "${character.species} - ${character.status}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
