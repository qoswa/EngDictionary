package com.qoswantin.engdictionary.dictionaryservice.model

import com.google.gson.annotations.SerializedName

data class MeaningModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("translation")
    val translation: TranslationModel,
    @SerializedName("images")
    val images: List<ImageModel>
) {
    data class ImageModel(
        @SerializedName("url")
        val imageUrl: String
    )
}
