package com.plcoding.cryptocurrencyappyt.presention.coin_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constant
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){


    private val _state  = mutableStateOf(CoinDetailState())

    val state:State<CoinDetailState> = _state

    init {

        savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)

        }

    }


    private fun getCoinDetail(coinId:String){

        getCoinUseCase(coinId).onEach { result ->

            when (result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(
                        coin = result.data
                    )
                }
                //vdvfdv
                is Resource.Error -> {

                    _state.value = CoinDetailState(
                        error = result.massage ?: "something gone wrong"
                    )
                }
                is Resource.Loading -> {

                    _state.value = CoinDetailState(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)


    }

}