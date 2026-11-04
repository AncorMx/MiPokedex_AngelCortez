package cortez.angel.pokedexlist.navigation

import kotlinx.serialization.Serializable

// Rutas de navegación de la aplicación
@Serializable
object Login

@Serializable
object PokemonList

@Serializable
data class PokemonDetail(val pokemon: Int)

@Serializable
object PokemonHunt
