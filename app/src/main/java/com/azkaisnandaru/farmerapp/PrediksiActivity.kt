package com.azkaisnandaru.farmerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.azkaisnandaru.farmerapp.adapter.DataAdapter
import com.azkaisnandaru.farmerapp.databinding.ActivityPrediksiBinding
import com.azkaisnandaru.farmerapp.model.HistoryPredictionItem
import com.azkaisnandaru.farmerapp.model.HistoryPredictionResponse
import com.azkaisnandaru.farmerapp.network.ApiConfig
import com.azkaisnandaru.farmerapp.network.ApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrediksiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrediksiBinding
    private lateinit var  prediksiadapter: DataAdapter
    private val prediksiList = ArrayList<HistoryPredictionItem>()
    private lateinit var auth: FirebaseAuth
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = ApiConfig.create()
        super.onCreate(savedInstanceState)
        binding = ActivityPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.fabAdd.setOnClickListener{
            startActivity(Intent(this@PrediksiActivity, TambahDataActivity::class.java))
        }

        getHistoryPrediction(auth.currentUser!!.email.toString())
        prediksiadapter = DataAdapter(prediksiList)

        binding.rvPrediksi.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvPrediksi.adapter = prediksiadapter
    }
    private fun getHistoryPrediction(email:String){
        val client = apiService.getHistoryPrediction(email)
        client.enqueue(object : Callback<HistoryPredictionResponse> {
            override fun onResponse(call: Call<HistoryPredictionResponse>, response: Response<HistoryPredictionResponse>) {
                if (response.isSuccessful) {
                    prediksiList.addAll(response.body()!!.data)

                } else {
                    Toast.makeText(this@PrediksiActivity, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<HistoryPredictionResponse>, t: Throwable) {
                Toast.makeText(this@PrediksiActivity, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                val intent = Intent(this@PrediksiActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_logout ->{
                auth.signOut()
                val intent = Intent(this@PrediksiActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

