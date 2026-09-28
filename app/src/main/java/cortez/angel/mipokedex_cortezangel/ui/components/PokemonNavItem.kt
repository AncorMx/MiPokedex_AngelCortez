package cortez.angel.mipokedex_cortezangel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.mipokedex_cortezangel.R
import cortez.angel.mipokedex_cortezangel.data.NavPokemon
import cortez.angel.mipokedex_cortezangel.ui.theme.GreyText

enum class NavDirection { PREVIOUS, NEXT }

@Composable
fun PokemonNavItem(
    navPokemon: NavPokemon,
    direction: NavDirection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = if (direction == NavDirection.PREVIOUS) Alignment.Start else Alignment.End,
        modifier = modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = navPokemon.imageRes),
            contentDescription = navPokemon.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (direction == NavDirection.PREVIOUS) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${navPokemon.name} ${navPokemon.numberFormatted}",
                    color = GreyText,
                    fontSize = 12.sp
                )
            } else {
                Text(
                    text = "${navPokemon.name} ${navPokemon.numberFormatted}",
                    color = GreyText,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
