package com.example.publickeypinningkmmwithktor.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.publickeypinningkmmwithktor.httpclient.CorrectPinningService
import com.example.publickeypinningkmmwithktor.httpclient.IncorrectPinningService
import com.example.publickeypinningkmmwithktor.httpclient.PinningService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val correctPinningService: CorrectPinningService,
    private val incorrectPinningService: IncorrectPinningService
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private fun fetchData(pinningService: PinningService) {
        viewModelScope.launch {
            _viewState.update {
                MainViewState(loading = true)
            }
            try {
                val data = pinningService.getData()
                _viewState.update {
                    MainViewState(data = data, loading = false)
                }
            } catch (e: Exception) {
                _viewState.update {
                    MainViewState(exception = e, loading = false)
                }
            }
        }
    }

    fun fetchDataWithCorrectPins() {
        fetchData(correctPinningService)
    }

    fun fetchDataWithIncorrectPins() {
        fetchData(incorrectPinningService)
    }
}

data class MainViewState(
    val data: String? = null, val exception: Throwable? = null, val loading: Boolean = false
)