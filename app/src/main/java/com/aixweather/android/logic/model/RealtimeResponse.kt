package com.aixweather.android.logic.model

import com.google.gson.annotations.SerializedName


//示例：https://api.caiyunapp.com/v2.5/auv03EiB3Zt9RZcn/116.353063,39.944876/realtime.json
// 大概类似于
/*
{
 "status": "ok",
 "result": {
    "realtime": {
        "temperature": 23.16,
        "skycon": "WIND"
        "air_quality": {
            "aqi": { "chn":17.0 }
            }
         }
     }
 }
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)
    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}