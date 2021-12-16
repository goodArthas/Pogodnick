package ram.khab.pogodnick.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ram.khab.pogodnick.R
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _stateLiveData: MutableLiveData<State> = MutableLiveData()
    val stateLiveData: LiveData<State> = _stateLiveData

    var dataListToUi by mutableStateOf(listOf<CardWeather>())
        private set

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        repository.getAllWeather()
            .collect {
                dataListToUi = it
            }
    }

    fun deleteWeatherCard(city: CardWeather) {
        viewModelScope.launch {
            repository.deleteWeather(city).collect()
            fetchData()
        }
    }

    fun saveCity(cityName: String) {
        viewModelScope.launch {
            repository.getWeatherByCityName(cityName)
                .onStart {
                    _stateLiveData.postValue(State.Loading)
                }
                .catch { exception ->
                    _stateLiveData.postValue(State.Error(R.string.error_something_wrong))
                }
                .collect {
                    _stateLiveData.postValue(State.Success)
                    repository.saveCity(it).collect()
                }
            repository.getAllWeather().collect {
                dataListToUi = it
            }
        }
    }

    sealed class State {
        object Loading : State()
        object Success : State()
        data class Error(val errorRes: Int) : State()
    }
}