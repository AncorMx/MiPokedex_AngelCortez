package cortez.angel.pokedexlist.ui.viewmodel

import cortez.angel.pokedexlist.domain.Pokemon

data class WhoIsThatPokemonState(
    val mystery: Pokemon? = null,
    val options: List<Pokemon> = emptyList(),
    val guess: Pokemon? = null,
    val rounds: Int = 0,
    val hits: Int = 0,
) {
    val revealed: Boolean get() = guess != null

    val isCorrect: Boolean get() = guess != null && guess.number == mystery?.number
}
