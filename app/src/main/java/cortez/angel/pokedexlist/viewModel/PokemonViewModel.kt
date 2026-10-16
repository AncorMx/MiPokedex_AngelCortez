package cortez.angel.pokedexlist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.domain.Pokemon

class PokemonViewModel : ViewModel() {

    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemon by mutableStateOf(listOf<Pokemon>())
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    var isPokemonCaptured by mutableStateOf(false)
        private set

    fun searchPokemon() {
        wildPokemon = pokemonList.random()
        isPokemonCaptured = false
        gonePokemon = false
    }

    fun capturePokemon() {
        wildPokemon?.let { pokemon ->
            val isCaptured = (1..2).random()
            if (isCaptured == 1) {
                capturedPokemon = capturedPokemon + pokemon
                gonePokemon = false
                isPokemonCaptured = true
            } else {
                gonePokemon = true
                isPokemonCaptured = false
            }
            wildPokemon = null
        }
    }

    fun getPrevPokemon(currentNumber: Int): Pokemon? {
        val currentIndex = pokemonList.indexOfFirst { it.number == currentNumber }
        return if (currentIndex > 0) pokemonList[currentIndex - 1] else null
    }

    fun getNextPokemon(currentNumber: Int): Pokemon? {
        val currentIndex = pokemonList.indexOfFirst { it.number == currentNumber }
        return if (currentIndex in 0 until pokemonList.lastIndex) pokemonList[currentIndex + 1] else null
    }

    fun getFavoritePokemon(): List<Pokemon> {
        return pokemonList.filter { it.favorite }
    }

    fun getPokemonByNumber(number: Int): Pokemon {
        return pokemonList.firstOrNull { it.number == number } ?: pokemonList.first()
    }

    fun toggleFavorite(number: Int): Boolean {
        val pokemon = pokemonList.find { it.number == number }
        pokemon?.let {
            it.favorite = !it.favorite
            return it.favorite
        }
        return false
    }
}
