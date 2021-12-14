package ram.khab.pogodnick.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class MainViewModel(
    val repository: Repository
) : ViewModel() {

    private val _weatherCardsData: MutableLiveData<List<CardWeather>> = MutableLiveData()
    val weatherCardsData: LiveData<List<CardWeather>> = _weatherCardsData

    fun fetch() {
        viewModelScope.launch {
            _weatherCardsData.postValue(repository.getWeather(""))

        }
    }


}