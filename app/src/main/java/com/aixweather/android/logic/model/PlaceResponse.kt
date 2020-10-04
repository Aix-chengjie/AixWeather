package com.aixweather.android.logic.model

import android.location.Location
import com.google.gson.annotations.SerializedName
//城市响应
data class PlaceResponse(val status: String, val places: List<Place>)

//城市名字和地址
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

//位置 经纬度
data class Location(val lng: String, val lat: String)
