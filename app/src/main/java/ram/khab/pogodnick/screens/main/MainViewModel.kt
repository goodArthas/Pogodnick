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
import ram.khab.pogodnick.model.State
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _stateLiveData: MutableLiveData<State> = MutableLiveData()
    val stateLiveData: LiveData<State> = _stateLiveData

    var dataListToUi by mutableStateOf(mapOf<Boolean, List<CardWeather>>())
        private set

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    init {
        updateWeather()
    }

    private suspend fun fetchDataFromDb() {
        repository.getAllWeather()
            .collect { listCardWeather ->
                dataListToUi =
                    listCardWeather.groupBy { it.favorite }.toSortedMap(compareBy { !it })
            }
    }

    fun updateWeather() {
        viewModelScope.launch {
            repository
                .getAllWeather()
                .onStart {
                    _stateLiveData.postValue(State.Loading)
                    _isRefreshing.emit(true)
                }
                .map {
                    return@map it.asFlow()
                        .flatMapMerge { cardWeather ->
                            repository.getWeather(cardWeather)
                        }
                        .toList()
                }
                .catch {
                    _isRefreshing.emit(false)
                    _stateLiveData.postValue(State.Error(R.string.error_update))
                    fetchDataFromDb()
                }.collect { listCardWeather ->
                    _isRefreshing.emit(false)
                    repository.updateWeather(listCardWeather).collect()
                    fetchDataFromDb()
                }
        }
    }

    fun updateFavoriteWeather(cardWeather: CardWeather) {
        viewModelScope.launch {
            repository.updateWeather(listOf(cardWeather)).collect()
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
            repository.getWeather(
                CardWeather(cityName = cityName, favorite = false, howDegrease = "")
            )
                .onStart {
                    _stateLiveData.postValue(State.Loading)
                }
                .catch { exception ->
                    _stateLiveData.postValue(State.Error(R.string.error_something_wrong))
                }
                .collect { cardWeather ->
                    _stateLiveData.postValue(State.Success)
                    repository.saveCity(cardWeather).collect()
                }
            repository.getAllWeather().collect { listCardWeather ->
                dataListToUi =
                    listCardWeather.groupBy { it.favorite }.toSortedMap(compareBy { !it })
            }
        }
    }

}