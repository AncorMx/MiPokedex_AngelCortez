package cortez.angel.pokedexlist.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.pokedexlist.data.bulbasur
import cortez.angel.pokedexlist.domain.Pokemon
import cortez.angel.pokedexlist.ui.theme.OffWhite
import cortez.angel.pokedexlist.utilities.getColorByType

@Composable
fun PokemonRow(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = pokemon.description,
                fontSize = 10.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Alto: ${pokemon.height}",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "Peso: ${pokemon.weight}",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        NumberChip(
            text = "${pokemon.number}",
            colors = colors,
            modifier = Modifier.align(Alignment.Top)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)
    val gradientBrush = Brush.sweepGradient(
        colors = listOf(
            colors.first,
            OffWhite,
            colors.first,
            OffWhite,
            colors.first
        )
    )

    Column(
        modifier = Modifier.padding(vertical = 15.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(5.dp, gradientBrush),
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .width(75.dp)
                        .padding(5.dp)
                )
            }

            NumberChip(
                text = "${pokemon.number}",
                colors = colors,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        NumberChip(
            text = "${pokemon.number}",
            colors = colors
        )
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .size(150.dp)
                .padding(10.dp)
        )
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "Alto: ${pokemon.height}",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Peso: ${pokemon.weight}",
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonElementPreview() {
    Column {
        PokemonRow(bulbasur)
        FavoritePokemon(bulbasur)
        PokemonCell(bulbasur)
    }
}
