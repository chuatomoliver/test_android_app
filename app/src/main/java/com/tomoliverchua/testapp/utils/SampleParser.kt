package com.tomoliverchua.testapp.utils

class SampleParser {

    fun convertStringToInt(value: String) : Int?{
        try {
            return value.toIntOrNull()
        } catch (e : Exception) {
            return null
        }
    }
}