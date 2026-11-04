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

import cortez.angel.pokedexlist.viewModel.PokemonViewModel
import cortez.angel.pokedexlist.data.bulbasur

import cortez.angel.pokedexlist.components.PokedexCard
import cortez.angel.pokedexlist.components.PokedexHeader
import cortez.angel.pokedexlist.components.PokedexNavigation
import cortez.angel.pokedexlist.components.PokemonStats
import cortez.angel.pokedexlist.components.TypeChips
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.text.style.TextAlign
import cortez.angel.pokedexlist.R
import cortez.angel.pokedexlist.ui.theme.Dimens
import cortez.angel.pokedexlist.domain.Pokemon
import cortez.angel.pokedexlist.viewModel.PokemonDetailState

private const val CARD_TOP = 0.29f
private const val IMAGE_TOP = 0.10f
private const val IMAGE_BOTTOM = 0.335f

private val DescriptionAlignment = BiasAlignment.Vertical(-0.5f)

@Composable
fun PokemonDetailScreen(
    innerPaddingValues: PaddingValues,
    pokemonId: Int,
    pokemonViewModel: PokemonViewModel,
    onBack: () -> Unit = {},
    onNavigateToDetail: (Int) -> Unit = {}
) {
    val detail = pokemonViewModel.detailOf(pokemonId)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPaddingValues)
            .statusBarsPadding(),
    ) {
        if (detail == null) {
            Text(
                text = "Pokemon no encontrado",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
            )
        } else {
            PokemonDetail(
                pokemon = detail.pokemon,
                previous = detail.previous,
                next = detail.next,
                onFavoriteClick = { pokemonViewModel.toggleFavorite(detail.pokemon.number) },
                onNeighborClick = { neighbor -> onNavigateToDetail(neighbor.number) },
            )
        }
    }
}

@Composable
private fun BoxScope.PokemonDetail(
    pokemon: Pokemon,
    previous: Pokemon,
    next: Pokemon,
    onFavoriteClick: () -> Unit,
    onNeighborClick: (Pokemon) -> Unit,
) {
    Image(
        painter = painterResource(R.drawable.ic_pokeball),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopEnd)
            .offset(x = Dimens.PokeballOffsetX, y = Dimens.PokeballOffsetY)
            .size(Dimens.PokeballSize),
    )
    PokedexCard(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxHeight(1f - CARD_TOP),
    ) {
        TypeChips(pokemon.type, Modifier.padding(top = Dimens.MarginTypeTop))
        PokemonStats(pokemon, Modifier.padding(top = Dimens.MarginMedium))
        Text(
            text = pokemon.description,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.MarginDescription)
                .wrapContentHeight(DescriptionAlignment),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        PokedexNavigation(previous = previous, next = next, onPokemonClick = onNeighborClick)
    }
    PokedexHeader(pokemon, onFavoriteClick)
    PokemonPortrait(pokemon)
}

@Composable
private fun PokemonPortrait(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(IMAGE_TOP))
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "Imagen de ${pokemon.name}",
            modifier = Modifier
                .weight(IMAGE_BOTTOM - IMAGE_TOP)
                .aspectRatio(1f),
        )
        Spacer(Modifier.weight(1f - IMAGE_BOTTOM))
    }
}


