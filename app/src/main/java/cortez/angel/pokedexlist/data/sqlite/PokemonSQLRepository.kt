package cortez.angel.pokedexlist.data.sqlite

import cortez.angel.pokedexlist.data.interfaces.IPokemonDAO
import cortez.angel.pokedexlist.data.interfaces.IPokemonRepository
import cortez.angel.pokedexlist.domain.Pokemon

class PokemonSQLRepository(private val dao: IPokemonDAO) : IPokemonRepository {

    override suspend fun addPokemon(pokemon: Pokemon) {
        if (isValid(pokemon)) dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemons(): List<Pokemon> = dao.getAllPokemons()

    override suspend fun getFavoritePokemons(): List<Pokemon> = dao.getFavoritePokemons()

    override suspend fun getPokemonsByType(type: String): List<Pokemon> =
        dao.getPokemonsByType(type.trim())

    override suspend fun updatePokemon(pokemon: Pokemon) {
        if (isValid(pokemon)) dao.updatePokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Int) {
        dao.getPokemonById(id)?.let { dao.deletePokemon(it) }
    }

    private fun isValid(pokemon: Pokemon): Boolean =
        pokemon.name.isNotBlank() && pokemon.number > 0
}
