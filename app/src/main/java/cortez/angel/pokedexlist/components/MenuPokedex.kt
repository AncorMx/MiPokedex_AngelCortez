package cortez.angel.pokedexlist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.domain.Pokemon

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, innerPadding: PaddingValues) {
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MenuPokedex(
        pokemonList = pokemonList,
        innerPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
    )
}
