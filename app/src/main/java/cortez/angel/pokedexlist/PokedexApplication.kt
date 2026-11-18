package cortez.angel.pokedexlist

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import cortez.angel.pokedexlist.data.sqlite.DatabaseHelper
import cortez.angel.pokedexlist.data.sqlite.PokemonDAO
import cortez.angel.pokedexlist.data.sqlite.PokemonSQLRepository

class PokedexApplication : Application() {
    private val databaseHelper by lazy { DatabaseHelper(this) }
    val pokemonSQLRepository by lazy { PokemonSQLRepository(PokemonDAO(databaseHelper)) }
}

val CreationExtras.pokedexApplication: PokedexApplication
    get() = checkNotNull(this[APPLICATION_KEY]) as PokedexApplication
