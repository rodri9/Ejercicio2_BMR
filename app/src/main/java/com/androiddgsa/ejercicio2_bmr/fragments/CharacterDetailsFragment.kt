package com.androiddgsa.ejercicio2_bmr.fragments

import android.os.Bundle
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androiddgsa.ejercicio2_bmr.R
import com.androiddgsa.ejercicio2_bmr.data.Api
import com.androiddgsa.ejercicio2_bmr.data.models.DetailsResponse
import com.androiddgsa.ejercicio2_bmr.databinding.FragmentCharacterDetailsBinding
import com.androiddgsa.ejercicio2_bmr.utils.Constants
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_ID = "id"

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)

        val call = api.getCharacterDetails(id)

        call.enqueue(object: Callback<DetailsResponse> {
            override fun onResponse(
                p0: Call<DetailsResponse?>,
                response: Response<DetailsResponse?>
            ) {
                binding.pbDetailsLoading.visibility = View.INVISIBLE

                if (response.isSuccessful) {

                    val detailResponse = response.body()?.data

                    binding.apply {
                        tvCharacterDetailsName.text = detailResponse?.nombre

                        Glide.with(requireContext())
                            .load(detailResponse?.imagen)
                            .placeholder(R.drawable.no_image_available)
                            .error(R.drawable.no_image_available)
                            .into(ivCharacterDetails)

                        val filmsDetails = if (detailResponse?.peliculas.isNullOrEmpty()) {
                            getString(R.string.no_hay_peliculas)
                        }else {
                            detailResponse?.peliculas?.joinToString("\n")
                        }
                        tvFilmsDetails.text = filmsDetails.toString()
                    }
                }else {
                    Log.e(Constants.LOGTAG, "Error: ${response.body()}")
                }
            }

            override fun onFailure(
                p0: Call<DetailsResponse?>,
                p1: Throwable
            ) {
                binding.pbDetailsLoading.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), getString(R.string.sin_conexi_n_favor_de_reintentar), Toast.LENGTH_LONG).show()            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id)
                }
            }
    }
}