package com.example.room_and_compose.view

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.room_and_compose.data.entities.Cities
import com.example.room_and_compose.viewmodel.ListCitiesViewModel

@Composable
fun ListCitiesView(
    viewModel: ListCitiesViewModel = hiltViewModel(), navController: NavController){
    var list = viewModel.citiesList.observeAsState(listOf())

    LazyColumn() {
        itemsIndexed(list.value) {
            index, city ->
            myCard(city = city, navController = navController)
        }
    }
}

@Composable
fun myCard(city: Cities, navController: NavController) {
    Card(
       modifier = Modifier
           .fillMaxSize()
           .padding(10.dp)
           .height(50.dp)
           .clickable {
                navController.currentBackStackEntry?.arguments = Bundle().apply {
                    putParcelable("city", city)
                }
               navController.navigate("alterationScreen")

           }, elevation = 8.dp 
    ) {
        Text(modifier = Modifier.padding(vertical = 10.dp),
            text = city.city_name.toString(),
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center))
    }
}