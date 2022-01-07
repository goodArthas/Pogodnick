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
import ram.khab.pogodnick.domain.CitySaverUseCase
import ram.khab.pogodnick.domain.RemoverCityUseCase
import ram.khab.pogodnick.domain.UpdaterDataInWeatherCardUseCase
import ram.khab.pogodnick.domain.WeatherCardLikeChangerUseCase
import ram.khab.pogodnick.model.State
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class MainViewModel(
    private val repository: Repository,
    private val removerCityUseCase: RemoverCityUseCase,
    private val likeChangerUseCase: WeatherCardLikeChangerUseCase,
    private val citySaverUseCase: CitySaverUseCase,
    private val updaterWeatherUseCase: UpdaterDataInWeatherCardUseCase
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
            updaterWeatherUseCase.execute()
                .onStart {
                    _stateLiveData.postValue(State.Loading)
                    _isRefreshing.emit(true)
                }
                .catch {
                    _isRefreshing.emit(false)
                    _stateLiveData.postValue(State.Error(R.string.error_update))
                    fetchDataFromDb()
                }.collect {
                    _isRefreshing.emit(false)
                    fetchDataFromDb()
                }
        }
    }

    fun changeFavoriteInWeatherCard(cardWeather: CardWeather) {
        viewModelScope.launch {
            likeChangerUseCase.execute(cardWeather).collect()
        }
    }

    fun deleteWeatherCard(city: CardWeather) {
        viewModelScope.launch {
            removerCityUseCase.execute(city).collect()
            fetchDataFromDb()
        }
    }

    fun saveCity(cityName: String) {
        viewModelScope.launch {
            checkExistCityInBd(cityName).collect { existCity ->
                if (!existCity) {
                    citySaverUseCase.execute(cityName)
                        .onStart {
                            _stateLiveData.postValue(State.Loading)
                        }
                        .catch { exception ->
                            _stateLiveData.postValue(State.Error(R.string.error_something_wrong))
                        }
                        .collect {
                            _stateLiveData.postValue(State.Success)
                        }
                    fetchDataFromDb()
                } else {
                    _stateLiveData.postValue(State.Error(R.string.error_city_alraedt_exist))
                }
            }
        }
    }

    private fun checkExistCityInBd(cityName: String): Flow<Boolean> = flow {
        repository
            .getAllWeather()
            .collect { listCardWeather ->
                val card = listCardWeather.filter { cardWeather ->
                    cardWeather.cityName.lowercase() == cityName.lowercase()
                }.firstOrNull()
                emit(card != null)
            }
    }

}