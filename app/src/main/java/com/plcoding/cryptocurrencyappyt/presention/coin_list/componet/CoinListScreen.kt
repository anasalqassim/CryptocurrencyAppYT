package com.plcoding.cryptocurrencyappyt.presention.coin_list.componet

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.presention.Screen
import com.plcoding.cryptocurrencyappyt.presention.coin_list.CoinListViewModel


@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxWidth()){
        LazyColumn( modifier = Modifier.fillMaxWidth()){
            items(state.coins){ coin ->
                CoinItemList(coin = coin , onItemClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")

                })




            }


        }
        if (state.error.isNotEmpty()){

            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)



            )

        }
        if (state.isLoading){
            CircularProgressIndicator()
        }
    }





}