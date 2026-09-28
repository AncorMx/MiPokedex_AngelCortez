package cortez.angel.mipokedex_cortezangel.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cortez.angel.mipokedex_cortezangel.ui.theme.TextRed

@Composable
fun PokemonStatItemRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = label,
            color = TextRed,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = value,
            color = Color.Black,
            fontSize = 18.sp
        )
    }
}

@Composable
fun PokemonStatItemStacked(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            color = TextRed,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = Color.Black,
            fontSize = 18.sp
        )
    }
}
