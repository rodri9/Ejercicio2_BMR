package com.androiddgsa.ejercicio2_bmr.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddgsa.ejercicio2_bmr.data.models.ItemsViewModel
import com.androiddgsa.ejercicio2_bmr.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide
import android.icu.text.SimpleDateFormat
import java.util.Locale
import com.androiddgsa.ejercicio2_bmr.R

class ItemAdapter(
    private val itemList: List<ItemsViewModel>,
    private val onItemClick: (ItemsViewModel) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.bind(item)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsViewModel) {
            binding.tvNombreP.text = item.nombre

            val originalDate = item.fecha
            val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val newFormat = SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
            val date = originalFormat.parse(originalDate)
            val newDate = newFormat.format(date)

            binding.tvFechaC.text = newDate
            Glide.with(binding.root.context)
                .load(item.imagen)
                .placeholder(R.drawable.no_image_available)
                .error(R.drawable.no_image_available)
                .into(binding.ivPersonaje)
        }
    }
}