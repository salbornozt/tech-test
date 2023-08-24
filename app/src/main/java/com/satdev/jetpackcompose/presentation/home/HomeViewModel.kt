package com.satdev.jetpackcompose.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satdev.jetpackcompose.domain.repository.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val serviceRepository: ServiceRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                serviceRepository.getServiceResult()
            }
        }
    }

}