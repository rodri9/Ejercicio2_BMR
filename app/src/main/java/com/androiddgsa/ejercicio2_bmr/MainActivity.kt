package com.androiddgsa.ejercicio2_bmr

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddgsa.ejercicio2_bmr.adapters.ItemAdapter
import com.androiddgsa.ejercicio2_bmr.data.Api
import com.androiddgsa.ejercicio2_bmr.data.models.ApiResponse
import com.androiddgsa.ejercicio2_bmr.data.models.ItemsViewModel
import com.androiddgsa.ejercicio2_bmr.databinding.ActivityMainBinding
import com.androiddgsa.ejercicio2_bmr.fragments.CharacterDetailsFragment
import com.androiddgsa.ejercicio2_bmr.fragments.CharactersListFragment
import com.androiddgsa.ejercicio2_bmr.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CharactersListFragment())
                .commit()
        }
    }
}