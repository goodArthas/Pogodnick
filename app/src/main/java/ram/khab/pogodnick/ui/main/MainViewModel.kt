package ram.khab.pogodnick.ui.main

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

    fun addCity(cardWeather: CardWeather) {
        viewModelScope.launch {
            repository.saveWeather(cardWeather)
            _weatherCardsData.postValue(listOf(repository.getWeatherByCityName(cardWeather.cityName)))
        }
    }

}