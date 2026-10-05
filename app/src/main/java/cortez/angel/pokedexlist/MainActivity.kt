package cortez.angel.pokedexlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cortez.angel.pokedexlist.screens.MenuPokedexScreen
import cortez.angel.pokedexlist.ui.theme.PokedexListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MenuPokedexScreen(innerPadding = innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexListTheme {
        MenuPokedexScreen(innerPadding = PaddingValues())
    }
}
