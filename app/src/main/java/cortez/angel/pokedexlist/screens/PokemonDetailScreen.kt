package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.pokedexlist.components.NumberChip
import cortez.angel.pokedexlist.data.getPokemonByNumber
import cortez.angel.pokedexlist.ui.theme.PokedexListTheme
import cortez.angel.pokedexlist.utilities.getColorByType

@Composable
fun PokemonDetailScreen(
    innerPaddingValues: PaddingValues,
    pokemonId: Int,
    onBack: () -> Unit = {}
) {
    val pokemon = getPokemonByNumber(pokemonId)
    val colors = getColorByType(pokemon.type)

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

            NumberChip(
                text = "#${pokemon.number}",
                colors = colors
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(colors.first.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier.size(160.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Alto", fontWeight = FontWeight.Bold)
                    Text("${pokemon.height} m")
                }
            }

            Card(
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Peso", fontWeight = FontWeight.Bold)
                    Text("${pokemon.weight} kg")
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
            pokemonId = 1
        )
    }
}
