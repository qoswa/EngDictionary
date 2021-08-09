package com.qoswantin.engdictionary.dictionaryservice.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslationModel(
    @Json(name = "text")
    val translationText: String
)
