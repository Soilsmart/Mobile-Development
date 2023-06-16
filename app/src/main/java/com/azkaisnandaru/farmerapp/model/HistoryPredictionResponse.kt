package com.azkaisnandaru.farmerapp.model

import com.google.gson.annotations.SerializedName

data class HistoryPredictionResponse(
    @field:SerializedName("code") val code: Int,
    @field:SerializedName("data") val data: List<HistoryPredictionItem>
)

data class HistoryPredictionItem(
    @field:SerializedName("uid") val uid: String,
    @field:SerializedName("periode_tanam") val periodeTanam: Int,
    @field:SerializedName("luas_panen") val luasPanen: Int,
    @field:SerializedName("predicted_output") val predictedOutput: String,
    @field:SerializedName("timestamp") val timestamp: String
)