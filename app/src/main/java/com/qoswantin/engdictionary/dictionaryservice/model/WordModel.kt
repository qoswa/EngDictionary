package com.qoswantin.engdictionary.dictionaryservice.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "text")
    val text: String,
    @Json(name = "meanings")
    val meanings: List<MeaningLightModel>
) {
    @JsonClass(generateAdapter = true)
    data class MeaningLightModel(
        @Json(name = "id")
        val id: Int,
        @Json(name = "translation")
        val translation: TranslationModel
    )
}
