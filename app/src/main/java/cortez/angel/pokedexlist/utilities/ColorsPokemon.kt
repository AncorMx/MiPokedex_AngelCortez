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
import cortez.angel.pokedexlist.ui.theme.Water

fun getColorByType(type: String): Pair<Color, Color> {
    val primaryType = type.split("/").firstOrNull()?.trim()?.lowercase() ?: ""

    val (bgColor, isLight) = when (primaryType) {
        "electric", "eléctrico", "electrico" -> Pair(Electric, true)
        "fairy", "hada" -> Pair(Fairy, true)
        "ice", "hielo" -> Pair(Ice, true)
        "grass", "planta" -> Pair(Grass, false)
        "fire", "fuego" -> Pair(Fire, false)
        "water", "agua" -> Pair(Water, false)
        "normal" -> Pair(Normal, false)
        "bug", "bicho" -> Pair(Bug, false)
        "poison", "veneno" -> Pair(Poison, false)
        "ground", "tierra" -> Pair(Ground, false)
        "rock", "roca" -> Pair(Rock, false)
        "flying", "volador" -> Pair(Flying, false)
        "fight", "fighting", "lucha" -> Pair(Fight, false)
        "psych", "psychic", "psíquico", "psiquico" -> Pair(Psych, false)
        "ghost", "fantasma" -> Pair(Ghost, false)
        "dragon", "dragón" -> Pair(Dragon, false)
        "dark", "siniestro" -> Pair(Dark, false)
        else -> Pair(Normal, false)
    }

    val textColor = if (isLight) DarkGray else OffWhite
    return Pair(bgColor, textColor)
}
