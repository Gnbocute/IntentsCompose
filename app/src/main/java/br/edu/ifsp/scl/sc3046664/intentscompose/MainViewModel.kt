package br.edu.ifsp.scl.sc3046664.intentscompose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val STRING_KEY = "string_key"
    }

    private val _stringState =
        MutableStateFlow(savedStateHandle[STRING_KEY] ?: "")

    val stringState: StateFlow<String> =
        _stringState.asStateFlow()

    fun updateString(value: String) {
        _stringState.value = value
        savedStateHandle[STRING_KEY] = value
    }
}