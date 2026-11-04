package cortez.angel.pokedexlist.viewModel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.domain.Pokemon
import kotlin.random.Random

// Probabilidad base de captura de un Pokémon
private const val CAPTURE_CHANCE = 0.5f

// ViewModel que gestiona el estado principal de la Pokédex.
// Contiene la lógica compartida de listas, estado de detalles, captura y favoritos.
class PokemonViewModel(
    pokemons: List<Pokemon> = pokemonList,
    private val captureChance: Float = CAPTURE_CHANCE,
) : ViewModel() {

    // Lista mutable y reactiva interna
    private val _pokemons = pokemons.toMutableStateList()
    val pokemons: List<Pokemon> get() = _pokemons

    // Lista de favoritos precalculada, reacciona a cambios en _pokemons
    val favorites: List<Pokemon> by derivedStateOf { _pokemons.filter { it.favorite } }

    var pokedexState by mutableStateOf(PokedexState())
        private set

    // Devuelve los detalles completos con navegación (anterior y siguiente)
    fun detailOf(number: Int): PokemonDetailState? {
        val index = _pokemons.indexOfFirst { it.number == number }
        if (index == -1) return null
        
        // Maneja la circularidad de la lista (infinito)
        return PokemonDetailState(
            pokemon = _pokemons[index],
            previous = _pokemons[(index - 1).mod(_pokemons.size)],
            next = _pokemons[(index + 1).mod(_pokemons.size)],
        )
    }

    // Cambia el estado del atributo favorito actual
    fun toggleFavorite(number: Int) {
        val index = _pokemons.indexOfFirst { it.number == number }
        if (index == -1) return
        
        val pokemon = _pokemons[index]
        _pokemons[index] = pokemon.copy(favorite = !pokemon.favorite)
    }

    // Inicia un encuentro con un Pokémon salvaje aleatorio
    fun searchPokemon() {
        pokedexState = pokedexState.copy(
            wildPokemon = _pokemons.random(),
            lastCaptured = null,
            escaped = null,
        )
    }

    // Ejecuta el evento de lanzar una pokebola y gestiona la captura
    fun capturePokemon() {
        val wild = pokedexState.wildPokemon ?: return
        pokedexState = if (Random.nextFloat() < captureChance) {
            pokedexState.copy(
                wildPokemon = null,
                lastCaptured = wild,
                team = listOf(wild) + pokedexState.team,
            )
        } else {
            pokedexState.copy(wildPokemon = null, escaped = wild)
        }
    }
}
