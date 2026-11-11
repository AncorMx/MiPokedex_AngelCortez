package cortez.angel.pokedexlist.ui.viewmodel

import cortez.angel.pokedexlist.domain.Pokemon

data class ProfileState(
    val name: String = "",
    val email: String = "",
    val partner: Pokemon? = null,
    val partnerDays: Int = 0,
    val isLoading: Boolean = true,
)
