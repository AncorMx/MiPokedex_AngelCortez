package cortez.angel.mipokedex_cortezangel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.mipokedex_cortezangel.R
import cortez.angel.mipokedex_cortezangel.data.PokemonData
import cortez.angel.mipokedex_cortezangel.ui.components.NavDirection
import cortez.angel.mipokedex_cortezangel.ui.components.PokemonHeader
import cortez.angel.mipokedex_cortezangel.ui.components.PokemonNavItem
import cortez.angel.mipokedex_cortezangel.ui.components.PokemonStatItemRow
import cortez.angel.mipokedex_cortezangel.ui.components.PokemonStatItemStacked
import cortez.angel.mipokedex_cortezangel.ui.components.PokemonWatermark
import cortez.angel.mipokedex_cortezangel.ui.components.TypeBadge
import cortez.angel.mipokedex_cortezangel.ui.theme.GreyText

@Composable
fun PokemonDetailScreen(
    pokemonData: PokemonData = PokemonData.sampleMew,
    onPrevClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pokemonData.backgroundColor)
    ) {
        // 1. Watermark pokeball background
        PokemonWatermark()

        // 2. Top Header (Name, Number, Favorite Star)
        PokemonHeader(
            name = pokemonData.name,
            number = pokemonData.number,
            isFavorite = isFavorite,
            onFavoriteToggle = { isFavorite = !isFavorite },
            modifier = Modifier.padding(top = 16.dp)
        )

        // 3. White Card Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 190.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
                    .padding(top = 110.dp, bottom = 24.dp)
            ) {

                // Type Badge
                TypeBadge(
                    type = pokemonData.type,
                    backgroundColor = pokemonData.typeColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Stats Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        PokemonStatItemRow(
                            label = stringResource(R.string.label_height),
                            value = pokemonData.height
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        PokemonStatItemRow(
                            label = stringResource(R.string.label_weight),
                            value = pokemonData.weight
                        )
                    }

                    PokemonStatItemStacked(
                        label = stringResource(R.string.label_ability),
                        value = pokemonData.ability
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Description
                Text(
                    text = pokemonData.description,
                    color = GreyText,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Bottom Navigation (Prev / Next)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    pokemonData.prevPokemon?.let { prev ->
                        PokemonNavItem(
                            navPokemon = prev,
                            direction = NavDirection.PREVIOUS,
                            onClick = onPrevClick
                        )
                    }

                    pokemonData.nextPokemon?.let { next ->
                        PokemonNavItem(
                            navPokemon = next,
                            direction = NavDirection.NEXT,
                            onClick = onNextClick
                        )
                    }
                }
            }
        }

        // 4. Overlapping Main Pokemon Image
        Image(
            painter = painterResource(id = pokemonData.mainImageRes),
            contentDescription = pokemonData.name,
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.TopCenter)
                .offset(y = 80.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen()
}
