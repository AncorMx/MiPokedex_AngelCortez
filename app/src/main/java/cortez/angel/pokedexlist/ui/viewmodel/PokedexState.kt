package cortez.angel.pokedexlist.ui.viewmodel

import cortez.angel.pokedexlist.domain.Pokemon

data class PokedexState(
    val wildPokemon: Pokemon? = null,
    val lastCaptured: Pokemon? = null,
    val escaped: Pokemon? = null,
    val team: List<Pokemon> = emptyList(),
)
