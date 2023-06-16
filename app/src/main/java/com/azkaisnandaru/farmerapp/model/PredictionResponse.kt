package com.azkaisnandaru.farmerapp.model

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("hasil_prediksi")
    val hasilPrediksi: Int
)