package com.plcoding.cryptocurrencyappyt.common



sealed class Resource<T> (val data:T? = null , val massage:String? = null){

    class Success<T>(data: T):Resource<T>(data)

    class Error<T>(data: T, massage: String? = null):Resource<T>(data,massage)

    class Loading<T>(data: T?):Resource<T>(data)


}