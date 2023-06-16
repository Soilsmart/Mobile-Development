package com.azkaisnandaru.farmerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.azkaisnandaru.farmerapp.databinding.*
import com.azkaisnandaru.farmerapp.model.PredictionResponse
import com.azkaisnandaru.farmerapp.network.ApiConfig
import com.azkaisnandaru.farmerapp.network.ApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahDataBinding
    private lateinit var apiService: ApiService
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataBinding.inflate(layoutInflater)
        apiService = ApiConfig.create()
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnTambah.setOnClickListener {
            val email = auth.currentUser?.email
            val periodeTanam = binding.etPeriodeTanam.text.toString()
            val luasPanen = binding.etLuasPanen.text.toString().toFloat()

            if (email!!.isNotEmpty() && periodeTanam != null && luasPanen != null) {

                tambahData(email, periodeTanam, luasPanen)
            } else {
                Toast.makeText(this, "Mohon lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun tambahData(email: String, periodeTanam: Date, luasPanen: Float) {
        val call = apiService.sendPredictionRequest(email, periodeTanam, luasPanen)
        call.enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                if (response.isSuccessful) {
                    val predictionResponse = response.body()
                    predictionResponse?.let {
                        val message = it.message
                        val hasilPrediksi = it.hasilPrediksi
                        Toast.makeText(this@TambahDataActivity, "$message: $hasilPrediksi", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@TambahDataActivity, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                Toast.makeText(this@TambahDataActivity, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}