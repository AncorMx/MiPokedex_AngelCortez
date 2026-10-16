package cortez.angel.pokedexlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cortez.angel.pokedexlist.screens.MenuPokedexScreen
import cortez.angel.pokedexlist.screens.PokemonDetailScreen
import cortez.angel.pokedexlist.screens.PokemonHuntScreen

@Composable
fun MyApp(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokemonList) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPaddingValues,
                onNavigateToDetail = { id ->
                    navController.navigate(PokemonDetail(pokemon = id))
                },
                onNavigateToHunt = {
                    navController.navigate(PokemonHunt)
                }
            )
        }
        composable<PokemonDetail> { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemon")
                ?: backStackEntry.toRoute<PokemonDetail>().pokemon
            PokemonDetailScreen(
                innerPaddingValues = innerPaddingValues,
                pokemonId = pokemonId,
                onBack = { navController.popBackStack() }
            )
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(
                innerPadding = innerPaddingValues,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
