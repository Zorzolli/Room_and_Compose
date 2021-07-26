package com.example.room_and_compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_and_compose.data.entities.Cities
import com.example.room_and_compose.data.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlterationViewModel @Inject constructor(private val dao: CityRepository): ViewModel(){
    var id = 0

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _cityCep = MutableLiveData<String>()
    val cityCep: LiveData<String> = _cityCep

    private val _cityUf = MutableLiveData<String>()
    val cityUf: LiveData<String> = _cityUf

    fun onChangeCityName(newValue: String) {
        _cityName.value = newValue
    }

    fun onChangeCityCep(newValue: String) {
        _cityCep.value = newValue
    }

    fun onChangeCityUf(newValue: String) {
        _cityUf.value = newValue
    }

    fun onSetCityId(newValue: Int) {
        id = newValue
    }

    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun change() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.updateCity(Cities(city_name = cityName.value, city_cep = cityCep.value,
                city_uf = cityUf.value, id = id))
                status.postValue(true)
            }
        }
    }

    fun remove() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.removeCity(Cities(city_name = cityName.value, city_cep = cityCep.value,
                    city_uf = cityUf.value, id = id))
                status.postValue(true)
            }
        }
    }
}