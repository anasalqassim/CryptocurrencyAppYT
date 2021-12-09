package com.plcoding.cryptocurrencyappyt.domain.model

import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId:String,
    val name:String,
    val description:String,
    val symbol:String,
    val rank:Int,
    val isActive:Boolean,
    val team:List<TeamMember>,
    val tags:List<String>
)
