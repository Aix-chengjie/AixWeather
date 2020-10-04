package com.aixweather.android.logic.network


import com.aixweather.android.AixWeatherApplication
import com.aixweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
//示例：https://api.caiyunapp.com/v2/place?token=auv03EiB3Zt9RZcn&lang=zh_CN&query=北京
    @GET("v2/place?token=${AixWeatherApplication.TOKEN}&lang=zh_CN")
fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>   //这里返回值Call，
    // 返回的json后面可以直接解析为PlaceResponse对象
}