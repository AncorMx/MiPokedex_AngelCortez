package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cortez.angel.pokedexlist.R
import cortez.angel.pokedexlist.components.FavoritesRow
import cortez.angel.pokedexlist.components.MenuPokedex
import cortez.angel.pokedexlist.components.PokedexGrid
import cortez.angel.pokedexlist.data.getFavoritePokemons
import cortez.angel.pokedexlist.data.pokemonList
import cortez.angel.pokedexlist.ui.theme.DarkGray
import cortez.angel.pokedexlist.ui.theme.OffWhite
import cortez.angel.pokedexlist.ui.theme.PokedexListTheme
import cortez.angel.pokedexlist.ui.theme.SwitchThumbColor
import cortez.angel.pokedexlist.ui.theme.SwitchTrackActiveColor
import cortez.angel.pokedexlist.ui.theme.SwitchTrackInactiveColor

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onNavigateToDetail: (Int) -> Unit = {},
    onNavigateToHunt: () -> Unit = {}
) {
    var isGrid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Pokédex",
                style = MaterialTheme.typography.titleLarge
            )
            Button(onClick = onNavigateToHunt) {
                Text("Catch Me")
            }
        }

        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
        )

        FavoritesRow(
            favoriteList = getFavoritePokemons(),
            onPokemonClick = onNavigateToDetail
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Todos mis pokemones",
                style = MaterialTheme.typography.labelLarge
            )

            Switch(
                checked = isGrid,
                onCheckedChange = { isGrid = it },
                thumbContent = {
                    val iconRes = if (isGrid) R.drawable.ic_grid_view else R.drawable.ic_list_view
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = if (isGrid) "Grid View" else "List View",
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = SwitchThumbColor,
                    checkedTrackColor = SwitchTrackActiveColor,
                    uncheckedThumbColor = OffWhite,
                    uncheckedTrackColor = SwitchTrackInactiveColor,
                    checkedIconColor = OffWhite,
                    uncheckedIconColor = DarkGray
                )
            )
        }

        if (isGrid) {
            PokedexGrid(
                pokemonList = pokemonList,
                modifier = Modifier.weight(1f),
                onPokemonClick = onNavigateToDetail
            )
        } else {
            MenuPokedex(
                pokemonList = pokemonList,
                modifier = Modifier.weight(1f),
                onPokemonClick = onNavigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    PokedexListTheme {
        MenuPokedexScreen(innerPadding = PaddingValues(10.dp, 15.dp))
    }
}
