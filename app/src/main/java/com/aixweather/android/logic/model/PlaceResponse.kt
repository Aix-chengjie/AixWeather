package com.aixweather.android.logic.model

import com.google.gson.annotations.SerializedName

//示例：https://api.caiyunapp.com/v2/place?token=auv03EiB3Zt9RZcn&lang=zh_CN&query=北京
//{"status":"ok","query":"\u5317\u4eac","places":[{"id":"ChIJuSwU55ZS8DURiqkPryBWYrk","location":{"lat":39.9041999,"lng":116.4073963},"place_id":"g-ChIJuSwU55ZS8DURiqkPryBWYrk","name":"\u5317\u4eac\u5e02","formatted_address":"\u4e2d\u56fd\u5317\u4eac\u5e02"},{"id":"B000A83M61","name":"\u5317\u4eac\u897f\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u4e30\u53f0\u533a \u83b2\u82b1\u6c60\u4e1c\u8def118\u53f7","location":{"lat":39.89491,"lng":116.322056},"place_id":"a-B000A83M61"},{"id":"B000A833V8","name":"\u5317\u4eac\u5317\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u897f\u57ce\u533a \u5317\u6ee8\u6cb3\u8def1\u53f7","location":{"lat":39.944876,"lng":116.353063},"place_id":"a-B000A833V8"},{"id":"B000A350CB","name":"\u5317\u4eac\u4e1c\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u671d\u9633\u533a \u767e\u5b50\u6e7e\u8def7\u53f7","location":{"lat":39.90242,"lng":116.485079},"place_id":"a-B000A350CB"},{"id":"B000A83C36","name":"\u5317\u4eac\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u4e1c\u57ce\u533a \u6bdb\u5bb6\u6e7e\u80e1\u540c\u753213\u53f7","location":{"lat":39.902779,"lng":116.427694},"place_id":"a-B000A83C36"}]}


//城市响应
//{"status":"ok","query":"\u5317\u4eac",
// "places":[{"id":"ChIJuSwU55ZS8DURiqkPryBWYrk",
data class PlaceResponse(val status: String, val places: List<Place>)

//城市名字和地址
// "name":"\u5317\u4eac\u5e02",
// "formatted_address":"\u4e2d\u56fd\u5317\u4eac\u5e02"},{"id":"B000A83M61","name":"\u5317\u4eac\u897f\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u4e30\u53f0\u533a \u83b2\u82b1\u6c60\u4e1c\u8def118\u53f7","location":{"lat":39.89491,"lng":116.322056},"place_id":"a-B000A83M61"},{"id":"B000A833V8","name":"\u5317\u4eac\u5317\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u897f\u57ce\u533a \u5317\u6ee8\u6cb3\u8def1\u53f7","location":{"lat":39.944876,"lng":116.353063},"place_id":"a-B000A833V8"},{"id":"B000A350CB","name":"\u5317\u4eac\u4e1c\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u671d\u9633\u533a \u767e\u5b50\u6e7e\u8def7\u53f7","location":{"lat":39.90242,"lng":116.485079},"place_id":"a-B000A350CB"},{"id":"B000A83C36","name":"\u5317\u4eac\u7ad9","formatted_address":"\u4e2d\u56fd \u5317\u4eac\u5e02 \u4e1c\u57ce\u533a \u6bdb\u5bb6\u6e7e\u80e1\u540c\u753213\u53f7","location":{"lat":39.902779,"lng":116.427694},"place_id":"a-B000A83C36"}]}

data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

//位置 经纬度
// "location":{"lat":39.9041999,"lng":116.4073963},"place_id":"g-ChIJuSwU55ZS8DURiqkPryBWYrk",
data class Location(val lng: String, val lat: String)
