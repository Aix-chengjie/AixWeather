package com.aixweather.android.logic

import androidx.lifecycle.liveData
import com.aixweather.android.logic.model.Place
import com.aixweather.android.logic.network.AixWeatherNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = AixWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") { //根据状态来处理
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}