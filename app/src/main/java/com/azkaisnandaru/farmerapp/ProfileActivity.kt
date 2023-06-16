package com.azkaisnandaru.farmerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azkaisnandaru.farmerapp.databinding.ActivityProfileBinding
import com.azkaisnandaru.farmerapp.model.UserProfile
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance().reference
        storageReference = FirebaseStorage.getInstance().reference

//        loadUserProfile()

        binding.tvNama.text = auth.currentUser?.displayName
        binding.tvEmail.text = auth.currentUser?.email
//        binding.tvNoHP.text = noHpText
//        binding.tvAlamat.text = alamatText
//        binding.tvNIK.text = nikText
    }

//    private fun loadUserProfile() {
//        val currentUser: FirebaseUser? = auth.currentUser
//        currentUser?.let { user ->
//            val userId = user.uid
//            val userRef = database.child("users").child(userId)
//            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val userProfile = snapshot.getValue(UserProfile::class.java)
//                    userProfile?.let { profile ->
//                        val namaText = user.displayName
//                        val emailText = user.email
//                        val noHpText = getString(R.string.nohp_label, profile.noHP)
//                        val alamatText = getString(R.string.alamat_label, profile.alamat)
//                        val nikText = getString(R.string.nik_label, profile.nik)
//
//                        binding.tvNama.text = namaText
//                        binding.tvEmail.text = emailText
//                        binding.tvNoHP.text = noHpText
//                        binding.tvAlamat.text = alamatText
//                        binding.tvNIK.text = nikText
//
//                        val profileImageUrl = profile.profileImageUrl
//                        Glide.with(this@ProfileActivity)
//                            .load(profileImageUrl)
//                            .into(binding.imageView)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    // Handle error
//                }
//            })
//        }
//    }
}
