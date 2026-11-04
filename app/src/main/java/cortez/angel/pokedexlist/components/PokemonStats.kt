package cortez.angel.pokedexlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cortez.angel.pokedexlist.domain.Pokemon
import cortez.angel.pokedexlist.ui.theme.Dimens

private const val LABEL_WEIGHT = 0.27f
private const val VALUE_WEIGHT = 0.285f
private const val ABILITY_WEIGHT = 0.445f

@Composable
fun PokemonStats(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(LABEL_WEIGHT + VALUE_WEIGHT),
            verticalArrangement = Arrangement.spacedBy(Dimens.StatsRowSpacing),
        ) {
            StatRow(
                label = "Altura",
                value = "${pokemon.height} m",
            )
            StatRow(
                label = "Peso",
                value = "${pokemon.weight} kg",
            )
        }
        StatColumn(
            label = "Habilidad",
            value = pokemon.ability,
            modifier = Modifier
                .weight(ABILITY_WEIGHT)
                .padding(end = Dimens.MarginMedium),
        )
    }
}

@Composable
fun StatRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier) {
        StatLabel(
            text = label,
            modifier = Modifier
                .weight(LABEL_WEIGHT)
                .alignByBaseline()
                .padding(end = Dimens.StatsGap),
            textAlign = TextAlign.End,
        )
        StatValue(
            text = value,
            modifier = Modifier
                .weight(VALUE_WEIGHT)
                .alignByBaseline()
                .padding(start = Dimens.StatsGap),
        )
    }
}

@Composable
fun StatColumn(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        StatLabel(text = label)
        StatValue(text = value)
    }
}

@Composable
private fun StatLabel(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun StatValue(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.headlineSmall,
    )
}
