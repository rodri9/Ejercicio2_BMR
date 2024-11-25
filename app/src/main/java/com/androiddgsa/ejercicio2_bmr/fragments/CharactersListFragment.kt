package com.androiddgsa.ejercicio2_bmr.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddgsa.ejercicio2_bmr.MainActivity
import com.androiddgsa.ejercicio2_bmr.R
import com.androiddgsa.ejercicio2_bmr.adapters.ItemAdapter
import com.androiddgsa.ejercicio2_bmr.data.Api
import com.androiddgsa.ejercicio2_bmr.data.models.ApiResponse
import com.androiddgsa.ejercicio2_bmr.databinding.FragmentCharacterDetailsBinding
import com.androiddgsa.ejercicio2_bmr.databinding.FragmentCharactersListBinding
import com.androiddgsa.ejercicio2_bmr.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersListFragment : Fragment() {

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)

        val call = api.getAllCharacters()

        call.enqueue(object: Callback<ApiResponse> {
            override fun onResponse(
                p0: Call<ApiResponse?>,
                response: Response<ApiResponse?>
            ) {
                binding.pbLoading.visibility = View.INVISIBLE

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val charactersList = apiResponse?.data ?: mutableListOf()

                    binding.apply {
                        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
                        recyclerView.adapter = ItemAdapter(charactersList) { character ->
                            character.id?.let { id ->
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragmentContainer, CharacterDetailsFragment.newInstance(id))
                                    .addToBackStack(null)
                                    .commit()
                            }
                        }
                    }
                }else {
                    Log.e(Constants.LOGTAG, "Error: ${response.body()}")
                }
            }

            override fun onFailure(
                p0: Call<ApiResponse?>,
                p1: Throwable
            ) {
                binding.pbLoading.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), getString(R.string.sin_conexi_n_favor_de_reintentar), Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}