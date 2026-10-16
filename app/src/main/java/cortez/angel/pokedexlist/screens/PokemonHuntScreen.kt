package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cortez.angel.pokedexlist.components.PokemonCell
import cortez.angel.pokedexlist.viewModel.PokemonViewModel

@Composable
fun PokemonHuntScreen(
    innerPadding: PaddingValues,
    viewModel: PokemonViewModel = PokemonViewModel(),
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
            Text("Buscar Pokémon Salvaje")
        }

        Spacer(modifier = Modifier.height(20.dp))

        viewModel.wildPokemon?.let { pokemon ->
            Text(
                text = "¡Apareció un ${pokemon.name} salvaje!",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            PokemonCell(pokemon = pokemon)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}")
            }
        }

        if (viewModel.isGone) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "¡El Pokémon escapó!",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
