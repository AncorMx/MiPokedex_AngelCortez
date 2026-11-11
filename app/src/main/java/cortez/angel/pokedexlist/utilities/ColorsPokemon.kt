package cortez.angel.pokedexlist.utilities

import androidx.compose.ui.graphics.Color
import cortez.angel.pokedexlist.ui.theme.Bug
import cortez.angel.pokedexlist.ui.theme.Dark
import cortez.angel.pokedexlist.ui.theme.DarkGray
import cortez.angel.pokedexlist.ui.theme.Dragon
import cortez.angel.pokedexlist.ui.theme.Electric
import cortez.angel.pokedexlist.ui.theme.Fairy
import cortez.angel.pokedexlist.ui.theme.Fight
import cortez.angel.pokedexlist.ui.theme.Fire
import cortez.angel.pokedexlist.ui.theme.Flying
import cortez.angel.pokedexlist.ui.theme.Ghost
import cortez.angel.pokedexlist.ui.theme.Grass
import cortez.angel.pokedexlist.ui.theme.Ground
import cortez.angel.pokedexlist.ui.theme.Ice
import cortez.angel.pokedexlist.ui.theme.Normal
import cortez.angel.pokedexlist.ui.theme.OffWhite
import cortez.angel.pokedexlist.ui.theme.Poison
import cortez.angel.pokedexlist.ui.theme.Psych
import cortez.angel.pokedexlist.ui.theme.Rock
import cortez.angel.pokedexlist.ui.theme.Steel
import cortez.angel.pokedexlist.ui.theme.Water

/**
 * Color del tipo de Pokémon y el color del texto que va encima:
 * OffWhite si el color del tipo es oscuro, DarkGray si es claro.
 */
fun getColorByType(type: String): Pair<Color, Color> =
    // Con dos tipos ("Grass/Poison") manda el primero
    when (type.substringBefore('/')) {
        "Normal" -> Normal to OffWhite
        "Water" -> Water to OffWhite
        "Fire" -> Fire to OffWhite
        "Psych", "Psychic" -> Psych to OffWhite
        "Ghost" -> Ghost to OffWhite
        "Bug" -> Bug to OffWhite
        "Poison" -> Poison to OffWhite
        "Grass" -> Grass to OffWhite
        "Ground" -> Ground to OffWhite
        "Rock" -> Rock to OffWhite
        "Dragon" -> Dragon to OffWhite
        "Dark" -> Dark to OffWhite
        "Steel" -> Steel to OffWhite
        "Electric" -> Electric to DarkGray
        "Fairy" -> Fairy to DarkGray
        "Fight", "Fighting" -> Fight to DarkGray
        "Flying" -> Flying to DarkGray
        "Ice" -> Ice to DarkGray
        else -> Normal to OffWhite
    }
