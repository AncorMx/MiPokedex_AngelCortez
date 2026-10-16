package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.pokedexlist.components.PokemonRow
import cortez.angel.pokedexlist.viewModel.PokemonViewModel

@Composable
fun PokemonHuntScreen(
    innerPadding: PaddingValues,
    viewModel: PokemonViewModel,
    onNavigateToDetail: (Int) -> Unit = {},
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = onBack) {
                Text("Volver")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.searchPokemon() }) {
            Text("Buscar Pokémon en la hierba")
        }

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.wildPokemon?.let { pokemon ->
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "¡Apareció un ${pokemon.name} salvaje!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}")
            }
        }

        if (viewModel.gonePokemon) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "¡El Pokémon se ha escapado! 💨",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        }

        if (viewModel.isPokemonCaptured) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "¡Has capturado al Pokémon! 🎉",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pokémones Capturados",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            items(viewModel.capturedPokemon) { pokemon ->
                PokemonRow(
                    pokemon = pokemon,
                    onClick = { onNavigateToDetail(pokemon.number) }
                )
            }
        }
    }
}
