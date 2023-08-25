package com.satdev.jetpackcompose.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satdev.jetpackcompose.domain.repository.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val serviceRepository: ServiceRepository) :
    ViewModel() {

    var uiState by mutableStateOf(
        HomeScreenUiState()
    )
        private set


    fun onEvent(events: HomeScreenEvents) {
        when (events) {
            HomeScreenEvents.OnGetSchemeClick -> {
                getSchema()
            }

            HomeScreenEvents.OnListTablesClick -> {
                getTables()
            }

            is HomeScreenEvents.OnQueryChange -> {
                uiState = uiState.copy(searchQuery = events.query, filterState = {
                    it != null && it.lowercase().contains(events.query)
                }, resultMessage = "")
            }
        }
    }

    private fun getSchema() {
        uiState = uiState.copy(loadingState = true)
        viewModelScope.launch {
            val resultMessage: String = try {
                withContext(Dispatchers.IO) {
                    serviceRepository.getServiceResult()
                }
                "Esquema creado satisfactoriamente"
            } catch (e: Exception) {
                "Ocurrio un error al obtener el esquema"
            }
            uiState = uiState.copy(loadingState = false, resultMessage = resultMessage)

        }
    }

    private fun getTables() {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                serviceRepository.getSchemaTables()
            }
            uiState = uiState.copy(tableNameList = list, resultMessage = "")
        }
    }

}

data class HomeScreenUiState(
    val tableNameList: List<String?> = listOf(),
    val searchQuery: String = "",
    val filterState: (String?) -> Boolean = { true },
    val loadingState: Boolean = false,
    val resultMessage: String = ""
)