package cortez.angel.mipokedex_cortezangel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cortez.angel.mipokedex_cortezangel.R

@Composable
fun BoxScope.PokemonWatermark(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_pokeball_watermark),
        contentDescription = null,
        modifier = modifier
            .align(Alignment.TopEnd)
            .offset(x = 80.dp, y = (-50).dp)
            .size(350.dp)
            .alpha(0.2f)
    )
}
