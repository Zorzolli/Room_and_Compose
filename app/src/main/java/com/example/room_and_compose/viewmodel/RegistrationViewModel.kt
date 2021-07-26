package com.example.room_and_compose.viewmodel

import androidx.compose.runtime.mutableStateOf
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
class RegistrationViewModel @Inject constructor(private val dao: CityRepository): ViewModel() {
    val cityName = mutableStateOf("")
    val cityCep = mutableStateOf("")
    val cityUf = mutableStateOf("")

    fun onChangeCityName(newValue: String) {
        cityName.value = newValue
    }

    fun onChangeCityCep(newValue: String) {
        cityCep.value = newValue
    }

    fun onChangeCityUf(newValue: String) {
        cityUf.value = newValue
    }

    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun Register() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.addCity(
                    Cities(city_name = cityName.value, city_cep = cityCep.value, city_uf = cityUf.value)
                )
                status.postValue(true)
            }
        }
    }
}