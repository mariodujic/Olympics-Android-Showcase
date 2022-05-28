package com.zero.olympics.screens.athletes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.network.model.Game
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AthletesViewModel @Inject constructor(
    private val repository: AthletesRepository
) : ViewModel() {

    private val _games: MutableStateFlow<Result<List<Game>>> = MutableStateFlow(Result.Loading)
    val games: StateFlow<Result<List<Game>>> = _games

    init {
        viewModelScope.launch {
            val result = repository.getGames().run {
                if (this is Result.Success) Result.Success(this.value.sortedByDescending { it.year })
                else this
            }
            _games.emit(result)
        }
    }
}