package com.azkaisnandaru.farmerapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DataPrediksi(
    val uid: String,
    val periodeTanam: Int,
    val luasPanen: Int,
    val predictedOutput: String,
    val timestamp: String
)

