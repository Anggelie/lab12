package com.uvg.lab12anggelie


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.lab12anggelie.data.UserDataStorePrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class ProfileViewModel(
    private val dataStoreUserPrefs: UserDataStorePrefs
) : ViewModel() {

    data class ProfileUiState(
        val username: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) {
        operator fun getValue(nothing: Nothing?, property: KProperty<*>): Any {
            TODO("Not yet implemented")
        }
    }

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                dataStoreUserPrefs.getUsername()
                    .collect { username ->
                        _uiState.value = _uiState.value.copy(
                            username = username ?: "",
                            isLoading = false
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                dataStoreUserPrefs.clearUsername()
                _uiState.value = _uiState.value.copy(
                    username = "",
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadCharacterDetails(characterId: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                ProfileViewModel(
                    dataStoreUserPrefs = UserDataStorePrefs(application.dataStore)
                )
            }
        }
    }
}