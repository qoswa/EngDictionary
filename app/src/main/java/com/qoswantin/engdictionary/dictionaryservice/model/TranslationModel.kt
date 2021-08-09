package com.qoswantin.engdictionary.dictionaryservice.model

import com.google.gson.annotations.SerializedName

data class TranslationModel(
    @SerializedName("text")
    val translationText: String
)
