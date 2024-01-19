package com.welldressedmen.nari

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.welldressedmen.nari.MainActivityUiState.Loading
import com.welldressedmen.nari.MainActivityUiState.Success

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val testData: Flow<String> = flowOf("Hello World!")     // TODO: Replace with actual data

    val uiState: StateFlow<MainActivityUiState> = testData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val testData: String) : MainActivityUiState
}
