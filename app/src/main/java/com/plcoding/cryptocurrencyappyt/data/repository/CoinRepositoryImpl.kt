package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailsDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    val api: CoinPaprikaApi
):CoinRepository {
    override suspend fun getCoins(): List<CoinDto> = api.getCoins()

    override suspend fun getCoinDetails(coinId: String): CoinDetailsDto = api.getCoinDetails(coinId)
}