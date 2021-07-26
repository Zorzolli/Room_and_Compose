package com.example.room_and_compose.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.room_and_compose.data.entities.Cities
import com.example.room_and_compose.viewmodel.AlterationViewModel

@Composable
fun AlterationView(viewModel: AlterationViewModel = hiltViewModel(), navController: NavController, cities: Cities) {
    val name: String by viewModel.cityName.observeAsState(cities.city_name.toString())
    val cep: String by viewModel.cityCep.observeAsState(cities.city_cep.toString())
    val uf: String by viewModel.cityUf.observeAsState(cities.city_uf.toString())
    viewModel.onChangeCityName(name)
    viewModel.onChangeCityCep(cep)
    viewModel.onChangeCityUf(uf)
    cities.id?.let { viewModel.onSetCityId(it) }

    val status = viewModel.status.observeAsState()

    if (status.value == true) {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newName ->
                viewModel.onChangeCityName(newName)
            },
            label = { Text(text = "Nome da cidade") })
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = cep,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newCep ->
                viewModel.onChangeCityCep(newCep)
            },
            label = { Text(text = "Cep da cidade") })
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = uf, modifier = Modifier.fillMaxWidth(), onValueChange = { newUf ->
            viewModel.onChangeCityUf(newUf)
        }, label = { Text(text = "UF da cidade") })
        Spacer(modifier = Modifier.height(20.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.change()
            }) {
            Text(text = "Alterar")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.remove()
            }) {
            Text(text = "Remover")
        }
    }
}