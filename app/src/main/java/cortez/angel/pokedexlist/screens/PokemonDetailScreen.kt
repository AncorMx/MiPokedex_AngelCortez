package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.pokedexlist.R
import cortez.angel.pokedexlist.components.NumberChip
import cortez.angel.pokedexlist.domain.Pokemon
import cortez.angel.pokedexlist.ui.theme.Electric
import cortez.angel.pokedexlist.ui.theme.OffWhite
import cortez.angel.pokedexlist.ui.theme.PokedexListTheme
import cortez.angel.pokedexlist.utilities.getColorByType
import cortez.angel.pokedexlist.viewModel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    innerPaddingValues: PaddingValues,
    pokemonId: Int,
    viewModel: PokemonViewModel,
    onNavigateToDetail: (Int) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val pokemon = viewModel.getPokemonByNumber(pokemonId)
    val colors = getColorByType(pokemon.type)
    var isFavorite by remember(pokemon.number) { mutableStateOf(pokemon.favorite) }

    val prevPokemon = viewModel.getPrevPokemon(pokemon.number)
    val nextPokemon = viewModel.getNextPokemon(pokemon.number)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBack) {
                Text("Volver")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                NumberChip(
                    text = "#${pokemon.number}",
                    colors = colors
                )

                IconButton(
                    onClick = {
                        isFavorite = viewModel.toggleFavorite(pokemon.number)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_favorite),
                        tint = if (isFavorite) Electric else Color.Gray,
                        contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(colors.first.copy(alpha = 0.25f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier.size(140.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = colors.first.copy(alpha = 0.15f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Tipo: ${pokemon.type}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Habilidad: ${pokemon.ability}",
                    fontSize = 14.sp
                )
                Text(
                    text = pokemon.description,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier.weight(1f).padding(end = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Alto", fontWeight = FontWeight.Bold)
                    Text("${pokemon.height} m")
                }
            }

            Card(
                modifier = Modifier.weight(1f).padding(start = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Peso", fontWeight = FontWeight.Bold)
                    Text("${pokemon.weight} kg")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (prevPokemon != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onNavigateToDetail(prevPokemon.number) }
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_prev),
                        contentDescription = "Anterior",
                        modifier = Modifier.size(24.dp)
                    )
                    Image(
                        painter = painterResource(id = prevPokemon.image),
                        contentDescription = prevPokemon.name,
                        modifier = Modifier.size(40.dp)
                    )
                    Text("#${prevPokemon.number}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Spacer(modifier = Modifier.size(1.dp))
            }

            if (nextPokemon != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onNavigateToDetail(nextPokemon.number) }
                        .padding(8.dp)
                ) {
                    Text("#${nextPokemon.number}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Image(
                        painter = painterResource(id = nextPokemon.image),
                        contentDescription = nextPokemon.name,
                        modifier = Modifier.size(40.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_next),
                        contentDescription = "Siguiente",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailPreview() {
    PokedexListTheme {
        PokemonDetailScreen(
            innerPaddingValues = PaddingValues(),
            pokemonId = 1,
            viewModel = remember { PokemonViewModel() }
        )
    }
}
