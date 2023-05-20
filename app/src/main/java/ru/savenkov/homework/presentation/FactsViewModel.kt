package ru.savenkov.homework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.savenkov.homework.di.FragmentScope
import ru.savenkov.homework.domain.usecase.GetRandomFactFromLocalUseCase
import ru.savenkov.homework.domain.usecase.GetRandomFactFromRemoteUseCase
import javax.inject.Inject

@FragmentScope
class FactsViewModel @Inject constructor(
    private val getRandomFactFromLocalUseCase: GetRandomFactFromLocalUseCase,
    private val getRandomFactFromRemoteUseCase: GetRandomFactFromRemoteUseCase
) {

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun loadStrings() {
        _state.value = MainState.Loading

        val fromLocal = getRandomFactFromLocalUseCase()
        val fromRemote = getRandomFactFromRemoteUseCase()

        _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
    }
}