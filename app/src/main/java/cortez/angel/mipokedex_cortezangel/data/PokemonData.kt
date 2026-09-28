package cortez.angel.mipokedex_cortezangel.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import cortez.angel.mipokedex_cortezangel.R
import cortez.angel.mipokedex_cortezangel.ui.theme.MewPink
import cortez.angel.mipokedex_cortezangel.ui.theme.TypePsychic

data class NavPokemon(
    val name: String,
    val numberFormatted: String,
    @DrawableRes val imageRes: Int
)

data class PokemonData(
    val name: String,
    val number: String,
    val type: String,
    val typeColor: Color,
    val backgroundColor: Color,
    val height: String,
    val weight: String,
    val ability: String,
    val description: String,
    @DrawableRes val mainImageRes: Int,
    val prevPokemon: NavPokemon?,
    val nextPokemon: NavPokemon?
) {
    companion object {
        val sampleMew = PokemonData(
            name = "Mew",
            number = "#151",
            type = "Psíquico",
            typeColor = TypePsychic,
            backgroundColor = MewPink,
            height = "0,4m",
            weight = "4,0kg",
            ability = "Sincronía",
            description = "Dicen que Mew posee el mapa genético de todos los Pokémon. Puede hacerse invisible cuando quiere, por lo que pasa inadvertido aunque haya gente cerca.",
            mainImageRes = R.drawable.mew,
            prevPokemon = NavPokemon("Celebi", "N.º 0251", R.drawable.celebi),
            nextPokemon = NavPokemon("Jirachi", "N.º 0385", R.drawable.jirachi)
        )
    }
}
