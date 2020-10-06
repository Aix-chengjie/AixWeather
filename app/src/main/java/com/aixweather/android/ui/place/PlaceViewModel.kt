package com.aixweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aixweather.android.logic.Repository
import com.aixweather.android.logic.dao.PlaceDAO
import com.aixweather.android.logic.model.Place

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    //switchmap函数 一旦query发生改变，就调用{query-> -------}里的函数，借此来将返回值当成一个可观测对象

    val placeLiveData = Transformations.switchMap(searchLiveData) {query ->
        Repository.searchPlaces(query)
    }

    //外部调用的接口
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
    fun savePlace(place: Place) = PlaceDAO.savePlace(place)
    fun getSavedPlace() = PlaceDAO.getSavedPlace()
    fun isPlaceSaved() = PlaceDAO.isPlaceSaved()
}