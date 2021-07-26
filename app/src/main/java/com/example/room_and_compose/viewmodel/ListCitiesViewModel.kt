package com.example.room_and_compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.room_and_compose.data.entities.Cities
import com.example.room_and_compose.data.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListCitiesViewModel @Inject constructor(private val dao : CityRepository): ViewModel(){
    val citiesList: LiveData<List<Cities>> = dao.getAllCities.asLiveData()
}