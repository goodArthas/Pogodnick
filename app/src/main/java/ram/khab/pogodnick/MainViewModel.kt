package ram.khab.pogodnick

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ram.khab.pogodnick.model.pojo.CardWeather

class MainViewModel : ViewModel() {

    private val _weatherCardsData: MutableLiveData<List<CardWeather>> = MutableLiveData()
    val weatherCardsData: LiveData<List<CardWeather>> = _weatherCardsData

    fun fetch() {
        viewModelScope.launch {
            _weatherCardsData.postValue(testCards)

        }
    }

    private val testCards = listOf<CardWeather>(
        CardWeather("Челны", "-5", false),
        CardWeather("Елабуга", "-6", true),
        CardWeather("Кукмор", "16", false),
        CardWeather("Москва", "-3", true),
        CardWeather("Питер", "9", true),
        CardWeather("Кавказ", "-1", false),
        CardWeather("Сочи", "0", true),
        CardWeather("Пермь", "4", false),
        CardWeather("Нижний новгород", "4", false),
        CardWeather("Бетьки", "4", false),
        CardWeather("Игенче", "4", false),
    )

}