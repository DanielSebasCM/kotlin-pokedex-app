package com.example.kotlin.mypokedexapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mypokedexapp.data.network.model.PokedexObject
import com.example.kotlin.mypokedexapp.data.network.model.PokemonBase
import com.example.kotlin.mypokedexapp.domain.PokemonListRequirement
import com.example.kotlin.mypokedexapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val pokedexObjectLiveData = MutableLiveData<PokedexObject>()
    private val pokemonListRequirement = PokemonListRequirement()


    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokemonListRequirement(Constants.MAX_POKEMON_NUMBER)
            Log.d("Salida", result?.count.toString())
            CoroutineScope(Dispatchers.Main).launch {
                val data = result ?: PokedexObject(0, ArrayList<PokemonBase>())
                pokedexObjectLiveData.postValue(data)
            }
        }
    }
}