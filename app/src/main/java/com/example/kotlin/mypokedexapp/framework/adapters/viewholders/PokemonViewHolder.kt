package com.example.kotlin.mypokedexapp.framework.adapters.viewholders

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.mypokedexapp.data.PokemonRepository
import com.example.kotlin.mypokedexapp.data.network.model.PokemonBase
import com.example.kotlin.mypokedexapp.data.network.model.pokemon.Pokemon
import com.example.kotlin.mypokedexapp.databinding.ItemPokemonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewHolder(private val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PokemonBase, context: Context) {
        binding.TVName.text = item.name
        getPokemonInfo(getPokemonNumber(item), binding.IVPhoto, context)
    }

    private fun getPokemonNumber(pokemon: PokemonBase): Int {
        //"https://pokeapi.co/api/v2/pokemon/23/"
        val url = pokemon.url
        var pokemonStringNumber: String = url.replace("https://pokeapi.co/api/v2/pokemon/", "")
        pokemonStringNumber = pokemonStringNumber.replace("/", "")

        return Integer.parseInt(pokemonStringNumber)
    }

    private fun getPokemonInfo(pokemonNumber: Int, imageView: ImageView, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val pokemonRepository = PokemonRepository()
            val result: Pokemon? = pokemonRepository.getPokemonInfo(pokemonNumber)
            CoroutineScope(Dispatchers.Main).launch {
                val urlImage = result?.sprites?.other?.official_artwork?.front_default

                val requestOptions = RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .priority(Priority.HIGH)

                Glide.with(context).load(urlImage)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }
}
