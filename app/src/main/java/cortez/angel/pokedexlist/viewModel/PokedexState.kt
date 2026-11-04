package cortez.angel.pokedexlist.viewModel

import cortez.angel.pokedexlist.domain.Pokemon

// Representa el modelo de estado general del modo cacería (Hunt)
data class PokedexState(
    // Pokemon activo como encuentro aleatorio
    val wildPokemon: Pokemon? = null,
    // Registra la última captura exitosa
    val lastCaptured: Pokemon? = null,
    // Registra el último pokemon que huyó
    val escaped: Pokemon? = null,
    // Equipo formado por los capturados
    val team: List<Pokemon> = emptyList(),
)
