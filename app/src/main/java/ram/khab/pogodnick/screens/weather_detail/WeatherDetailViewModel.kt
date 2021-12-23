package ram.khab.pogodnick.screens.weather_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ram.khab.pogodnick.model.State
import ram.khab.pogodnick.model.pojo.WeatherDetails
import ram.khab.pogodnick.model.repository.Repository

class WeatherDetailViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _stateLiveData: MutableLiveData<State> = MutableLiveData()
    val stateLiveData: LiveData<State> = _stateLiveData

    var dataToUi by mutableStateOf(WeatherDetails())
        private set
}