package com.aixweather.android.logic.dao

import android.content.Context
import com.aixweather.android.AixWeatherApplication
import com.aixweather.android.logic.model.Location
import com.aixweather.android.logic.model.Place
import com.google.gson.Gson

object PlaceDAO {
    fun savePlace(place: Place) {
        sharedPreferences().edit().putString("place", Gson().toJson(place))
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
//        if (placeJson != null) {
//            return Gson().fromJson(placeJson, Place::class.java)
//        } else {
//            return Place("", Location("", ""), "")
//        }
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")
    private fun sharedPreferences() = AixWeatherApplication.context.getSharedPreferences("aix_weather", Context.MODE_PRIVATE)
}