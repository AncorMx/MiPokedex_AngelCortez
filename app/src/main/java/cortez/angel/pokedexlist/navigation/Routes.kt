package cortez.angel.pokedexlist.navigation

import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Serializable
data class PokemonDetail(val pokemon: Int)

@Serializable
object PokemonHunt
