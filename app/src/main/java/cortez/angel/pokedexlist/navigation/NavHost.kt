package cortez.angel.pokedexlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cortez.angel.pokedexlist.screens.LoginScreen
import cortez.angel.pokedexlist.screens.MenuPokedexScreen
import cortez.angel.pokedexlist.screens.PokemonDetailScreen
import cortez.angel.pokedexlist.screens.PokemonHuntScreen
import cortez.angel.pokedexlist.viewModel.PokemonViewModel
import cortez.angel.pokedexlist.viewModel.UserViewModel

@Composable
fun MyApp(
    innerPaddingValues: PaddingValues,
    userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory),
    pokemonViewModel: PokemonViewModel = viewModel()
) {
    val navController = rememberNavController()
    val startDest = if (userViewModel.isLoggedIn()) PokemonList else Login
    
    NavHost(navController = navController, startDestination = startDest) {
        composable<Login> {
            LoginScreen(
                isLoggedIn = userViewModel.isLoggedIn(),
                loginError = userViewModel.loginError,
                errorDescription = userViewModel.errorDescription,
                onLoginClick = userViewModel::login,
                onLoggedIn = {
                    navController.navigate(PokemonList) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }
        
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPaddingValues,
                userViewModel = userViewModel,
                pokemonViewModel = pokemonViewModel,
                onNavigateToDetail = { id ->
                    navController.navigate(PokemonDetail(pokemon = id))
                },
                onNavigateToHunt = {
                    navController.navigate(PokemonHunt)
                },
                onLogoutClick = {
                    navController.navigate(Login) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable<PokemonDetail> { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemon")
                ?: backStackEntry.toRoute<PokemonDetail>().pokemon
            PokemonDetailScreen(
                innerPaddingValues = innerPaddingValues,
                pokemonId = pokemonId,
                pokemonViewModel = pokemonViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(
                innerPadding = innerPaddingValues,
                pokemonViewModel = pokemonViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
