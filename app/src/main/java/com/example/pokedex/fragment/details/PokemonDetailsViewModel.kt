package com.example.pokedex.fragment.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.pokedex.data.remote.response.Pokemon
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.utils.Resource
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var isLoading = false

    val loadError: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    val pokemonInfo: MutableLiveData<Pokemon> by lazy {
        MutableLiveData<Pokemon>()
    }

    fun getPokemonInfo(pokemonName: String) {
        viewModelScope.launch {
            when (val result = repository.getPokemonInfo(pokemonName.lowercase())) {
                is Resource.Success -> {
                    if (result.data != null) pokemonInfo.value = result.data

                    loadError.value = ""
                    isLoading = false
                }
                is Resource.Error -> {
                    loadError.value = result.message.toString()
                    isLoading = false
                }
            }
        }
    }

}