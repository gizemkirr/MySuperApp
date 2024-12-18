package com.example.feature.weathers.data.model

enum class Cities(val cityName: String) {
    ANKARA("Ankara"),
    ISTANBUL("İstanbul"),
    IZMIR("İzmir");

    companion object {
        fun findName(cityName: String?) = entries.find { it.cityName == cityName }
    }
}