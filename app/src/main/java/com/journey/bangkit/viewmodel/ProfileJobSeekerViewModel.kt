package com.journey.bangkit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.User
import com.journey.bangkit.repository.ProfileJobSeekerRepository
import com.journey.bangkit.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileJobSeekerViewModel(private val repository: ProfileJobSeekerRepository) : ViewModel() {
    private val _user: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    val user: StateFlow<UiState<User>> get() = _user

    fun getUser() {
        viewModelScope.launch {
            repository.getUserData()
                .catch {
                    _user.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _user.value = UiState.Success(result)
                }
        }
    }
}