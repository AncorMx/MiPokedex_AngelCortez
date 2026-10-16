package cortez.angel.pokedexlist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.domain.Pokemon

class PokemonViewModel {
    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    val capturedPokemon = mutableStateListOf<Pokemon>()

    var isGone by mutableStateOf(false)
        private set

    fun searchPokemon() {
        wildPokemon = pokemonList.random()
        isGone = false
    }

    fun capturePokemon() {
        wildPokemon?.let { pokemon ->
            val isCaptured = (1..2).random()
            if (isCaptured == 1) {
                capturedPokemon.add(pokemon)
                isGone = false
                wildPokemon = null
            } else {
                isGone = true
            }
        }
    }
}
