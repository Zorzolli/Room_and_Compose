package com.example.room_and_compose.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.room_and_compose.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreenView(viewModel: RegistrationViewModel = hiltViewModel(), navController: NavController) {
    val name = viewModel.cityName.value
    val cep = viewModel.cityCep.value
    val uf = viewModel.cityUf.value
    val status = viewModel.status.observeAsState()
    
    if (status.value == true) {
        navController.popBackStack()
    }
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = name, modifier = Modifier.fillMaxWidth(), onValueChange = {
            newName -> viewModel.onChangeCityName(newName)
        }, label = { Text(text = "Nome da cidade")})
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = cep, modifier = Modifier.fillMaxWidth(), onValueChange = {
                newCep -> viewModel.onChangeCityCep(newCep)
        }, label = { Text(text = "Cep da cidade")})
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = uf, modifier = Modifier.fillMaxWidth(), onValueChange = {
                newUf -> viewModel.onChangeCityUf(newUf)
        }, label = { Text(text = "UF da cidade")})
        Spacer(modifier = Modifier.height(20.dp))
        
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
            viewModel.Register()
        }) {
            Text(text = "Cadastrar")
        }
    }
}