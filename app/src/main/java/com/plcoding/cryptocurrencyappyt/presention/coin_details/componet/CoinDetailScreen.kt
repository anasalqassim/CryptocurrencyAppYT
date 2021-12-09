package com.plcoding.cryptocurrencyappyt.presention.coin_details.componet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.presention.Screen
import com.plcoding.cryptocurrencyappyt.presention.coin_details.CoinDetailViewModel
import com.plcoding.cryptocurrencyappyt.presention.coin_list.CoinListViewModel


@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxWidth()){
        Log.e("fromDetail","sdoicj ${state.coin?.coinId}")
        state.coin?.let { coin ->
            Log.e("fromDetail","sdoicj ${coin.coinId}")
            LazyColumn( modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(20.dp)   ){
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )

                        Text(text = if (coin.isActive) "active" else "inActive",
                            color = if (coin.isActive) Color.Green else Color.Red ,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = coin.description,
                    style = MaterialTheme.typography.body2)

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = "Tags",
                    style = MaterialTheme.typography.h3)

                    Spacer(modifier = Modifier.height(15.dp))

                   FlowRow(mainAxisSpacing = 10.dp,
                   crossAxisSpacing = 10.dp,
                   modifier = Modifier.fillMaxWidth()) {

                       coin.tags.forEach { tag ->

                           CoinTag(tag = tag)


                       }


                   }
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = "Team Members",
                        style = MaterialTheme.typography.h3)

                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coin.team){ item: TeamMember -> 
                TeamMemberItem(teamMember = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))

                    Divider()

                    
                }

            }

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