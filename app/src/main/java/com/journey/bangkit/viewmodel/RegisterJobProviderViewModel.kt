package com.journey.bangkit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.journey.bangkit.data.model.City
import com.journey.bangkit.data.model.Province
import com.journey.bangkit.data.model.Sector
import com.journey.bangkit.repository.JobProviderRepository
import com.journey.bangkit.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RegisterJobProviderViewModel(private val repository: JobProviderRepository) : ViewModel() {
    private val _region: MutableStateFlow<UiState<Triple<List<Province>, List<City>, List<Sector>>>> = MutableStateFlow(UiState.Loading)
    val region: StateFlow<UiState<Triple<List<Province>, List<City>, List<Sector>>>> get() = _region

    fun getRegion(context: Context, provinceId: Int = 11) {
        viewModelScope.launch {
            repository.getProvinceCitySector(context, provinceId)
                .catch {
                    _region.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _region.value = UiState.Success(result)
                }
        }
    }
}