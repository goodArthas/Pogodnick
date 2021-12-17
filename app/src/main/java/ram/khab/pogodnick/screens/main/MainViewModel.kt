package ram.khab.pogodnick.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
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
            updateWeather()
        }
    }

    private suspend fun fetchDataFromDb() {
        repository.getAllWeather()
            .collect {
                dataListToUi = it
            }
    }

    private suspend fun updateWeather() {
        repository
            .getAllWeather()
            .onStart {
                _stateLiveData.postValue(State.Loading)
            }
            .map {
                return@map it.asFlow()
                    .flatMapMerge {
                        repository.getWeatherByCityName(it.cityName, it.uid)
                    }
                    .toList()
            }
            .catch { exception ->
                _stateLiveData.postValue(State.Error(R.string.error_update))
                fetchDataFromDb()
            }.collect {
                repository.updateWeather(it).collect()
                fetchDataFromDb()
            }
    }

    fun deleteWeatherCard(city: CardWeather) {
        viewModelScope.launch {
            repository.deleteWeather(city).collect()
            fetchDataFromDb()
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