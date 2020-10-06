package com.aixweather.android.logic.network

import com.aixweather.android.AixWeatherApplication
import com.aixweather.android.logic.model.DailyResponse
import com.aixweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    //示例：https://api.caiyunapp.com/v2.5/auv03EiB3Zt9RZcn/116.353063,39.944876/realtime.json
    @GET("v2.5/${AixWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>
    //示例：https://api.caiyunapp.com/v2.5/auv03EiB3Zt9RZcn/116.353063,39.944876/daily.json
    @GET("v2.5/${AixWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}