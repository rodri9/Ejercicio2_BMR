package com.androiddgsa.ejercicio2_bmr.fragments

import android.os.Bundle
import android.provider.Settings.Global.putString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androiddgsa.ejercicio2_bmr.R
import com.androiddgsa.ejercicio2_bmr.databinding.FragmentCharacterDetailsBinding

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