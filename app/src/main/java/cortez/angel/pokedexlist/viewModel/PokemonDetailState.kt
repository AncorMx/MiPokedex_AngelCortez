package cortez.angel.pokedexlist.viewModel

import cortez.angel.pokedexlist.domain.Pokemon

// Representa el modelo de datos necesarios para la pantalla de detalle de un Pokemon
data class PokemonDetailState(
    // Pokémon principal mostrado
    val pokemon: Pokemon,
    // Referencia al Pokémon anterior
    val previous: Pokemon,
    // Referencia al Pokémon siguiente
    val next: Pokemon,
)
