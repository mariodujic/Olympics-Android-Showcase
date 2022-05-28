package com.zero.olympics.screens.athletes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.domain.model.GameAthletes
import com.zero.olympics.screens.athletes.domain.usercase.GetGameAthletes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AthletesViewModel @Inject constructor(
    private val getGameAthletes: GetGameAthletes
) : ViewModel() {

    private val _games = MutableStateFlow<Result<List<GameAthletes>>>(Result.Loading)
    val games: StateFlow<Result<List<GameAthletes>>> = _games

    init {
        viewModelScope.launch {
            val result = getGameAthletes()
            _games.emit(result)
        }
    }
}