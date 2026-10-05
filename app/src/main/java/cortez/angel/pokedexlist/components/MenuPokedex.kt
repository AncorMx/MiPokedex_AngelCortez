package cortez.angel.pokedexlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.domain.Pokemon

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>) {
    LazyColumn {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Composable
fun FavoritesRow(favoriteList: List<Pokemon>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(favoriteList) { pokemon ->
            FavoritePokemon(pokemon)
        }
    }
}

@Composable
fun PokedexGrid(pokemonList: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        items(pokemonList) { pokemon ->
            PokemonCell(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MenuPokedex(pokemonList = pokemonList)
}
