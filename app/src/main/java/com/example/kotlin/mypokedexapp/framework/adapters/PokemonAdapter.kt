package com.example.kotlin.mypokedexapp.framework.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mypokedexapp.data.network.model.PokemonBase
import com.example.kotlin.mypokedexapp.framework.adapters.viewholders.PokemonViewHolder
import com.example.kotlin.mypokedexapp.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {
    var data: ArrayList<PokemonBase> = ArrayList()
    lateinit var context: Context
    @SuppressLint("NotConstructor")
    fun PokemonAdapter(basicData: ArrayList<PokemonBase>, context: Context) {
        this.data = basicData
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }
}