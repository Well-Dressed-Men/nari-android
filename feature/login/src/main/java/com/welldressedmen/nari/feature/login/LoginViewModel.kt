package com.welldressedmen.nari.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welldressedmen.nari.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel(
) {

//    val loginUiState: StateFlow<>

    fun changeLoginState() {
        viewModelScope.launch {
            userDataRepository.setIsLogin(true)
        }
    }
}