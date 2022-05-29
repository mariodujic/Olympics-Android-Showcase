package com.zero.olympics.screens.athletes.details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.navigation.AthleteDetails
import com.zero.olympics.screens.athletes.details.domain.usecase.GetAthlete
import com.zero.olympics.screens.athletes.domain.model.Athlete
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AthleteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAthlete: GetAthlete
) : ViewModel() {

    private val athleteId = savedStateHandle.get<String>(AthleteDetails.id)

    private val _athlete = MutableStateFlow<Result<Athlete>>(Result.Loading)
    val athlete: StateFlow<Result<Athlete>> = _athlete

    init {
        viewModelScope.launch {
            _athlete.emit(getAthlete(athleteId!!))
        }
    }
}