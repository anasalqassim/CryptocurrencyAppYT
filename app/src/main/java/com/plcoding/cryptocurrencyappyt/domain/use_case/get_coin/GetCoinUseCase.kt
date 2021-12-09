package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId:String):Flow<Resource<CoinDetail>> = flow {

        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinDetails(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e:HttpException){
            emit(Resource.Error<CoinDetail>(massage = e.localizedMessage ?: "An unexpected error" ))

        }catch (ioe:IOException){
            emit(Resource.Error<CoinDetail>(massage = "Can't reach the server plz check the internet connection" ))
        }


    }
}