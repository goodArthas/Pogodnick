package ram.khab.pogodnick.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _weatherCardsData: MutableLiveData<List<CardWeather>> = MutableLiveData()
    val weatherCardsData: LiveData<List<CardWeather>> = _weatherCardsData

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        _weatherCardsData.postValue(repository.getAllWeather().toList())
    }

    fun deleteWeatherCard(city: CardWeather) {
        viewModelScope.launch {
            repository.deleteWeather(city)
            fetchData()
        }
    }

    fun saveCity(cityName: String) {
        viewModelScope.launch {
            val result = repository.getWeatherByCityName(cityName)
            repository.saveCity(result)
            _weatherCardsData.postValue(repository.getAllWeather())
        }
    }

}