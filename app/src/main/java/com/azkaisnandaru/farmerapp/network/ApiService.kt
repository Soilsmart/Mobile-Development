package com.azkaisnandaru.farmerapp.network

import com.azkaisnandaru.farmerapp.model.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface ApiService {
    @GET("/api/historyprediction")
    fun getHistoryPrediction(@Query("email") email: String): Call<HistoryPredictionResponse>

    @POST("/api/predict")
    fun sendPredictionRequest(
        @Query("email") email: String,
        @Query("periode_tanam") periodeTanam: String,
        @Query("luas_panen") luasPanen: Float
    ): Call<PredictionResponse>
}