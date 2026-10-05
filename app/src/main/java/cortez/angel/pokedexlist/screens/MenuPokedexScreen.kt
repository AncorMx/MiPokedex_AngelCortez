package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cortez.angel.pokedexlist.components.FavoritesRow
import cortez.angel.pokedexlist.components.PokedexGrid
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.ui.theme.PokedexListTheme

@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues) {
    val favoriteList = pokemonList.filter { it.favorite }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        FavoritesRow(favoriteList = favoriteList)

        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        PokedexGrid(
            pokemonList = pokemonList,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    PokedexListTheme {
        MenuPokedexScreen(innerPadding = PaddingValues())
    }
}
