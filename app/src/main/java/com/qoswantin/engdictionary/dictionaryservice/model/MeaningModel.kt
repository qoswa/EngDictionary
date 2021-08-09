package com.qoswantin.engdictionary.dictionaryservice.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeaningModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "text")
    val text: String,
    @Json(name = "translation")
    val translation: TranslationModel,
    @Json(name = "images")
    val images: List<ImageModel>
) {
    @JsonClass(generateAdapter = true)
    data class ImageModel(
        @Json(name = "url")
        val imageUrl: String
    )
}
