package com.qoswantin.engdictionary.wordssearch.model

import com.google.gson.annotations.SerializedName

data class WordModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("meanings")
    val meanings: List<MeaningLightModel>
) {
    data class MeaningLightModel(
        @SerializedName("id")
        val id: Int,
        @SerializedName("translation")
        val translation: TranslationModel
    ) {
        data class TranslationModel(
            @SerializedName("text")
            val translationText: String
        )
    }
}
