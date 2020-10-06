package com.aixweather.android.logic

import androidx.lifecycle.liveData
import com.aixweather.android.logic.dao.PlaceDAO
import com.aixweather.android.logic.model.Place
import com.aixweather.android.logic.model.Weather
import com.aixweather.android.logic.network.AixWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    //获取城市 Dispatchers.IO函数线程类型设置，里面的代码全在子线程运行
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = AixWeatherNetWork.searchPlaces(query)
        if (placeResponse.status == "ok") { //根据状态来处理
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    //刷新天气
    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        //coroutineScope作用于任何其他挂起函数，并创建一个子作用域。即可给任意挂起函数提供协程作用域
        coroutineScope {
            //async函数创建一个子协程，调用await函数可以得到返回结果
            val deferredRealtime = async {
                AixWeatherNetWork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                AixWeatherNetWork.getDailyWeather(lng, lat)
            }
            //变相同时获得结果
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }

        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }
    fun savePlace(place: Place) = PlaceDAO.savePlace(place)
    fun getSavedPlace() = PlaceDAO.getSavedPlace()
    fun isPlaceSaved() = PlaceDAO.isPlaceSaved()
}