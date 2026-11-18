package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cortez.angel.pokedexlist.domain.Pokemon
import cortez.angel.pokedexlist.viewModel.AddPokemonViewModel

@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues,
    addPokemonViewModel: AddPokemonViewModel = viewModel(factory = AddPokemonViewModel.Factory)
) {
    val pokemonList by addPokemonViewModel.pokemons.collectAsState()
    
    AddPokemonContent(
        innerPadding = innerPadding,
        pokemonList = pokemonList,
        pokemonSaved = addPokemonViewModel.pokemonSaved,
        onSaveClick = addPokemonViewModel::savePokemon
    )
}

@Composable
private fun AddPokemonContent(
    innerPadding: PaddingValues,
    pokemonList: List<Pokemon>,
    pokemonSaved: Boolean,
    onSaveClick: (name: String, type: String, number: Int, description: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Agregar Pokémon (SQLite)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            PokemonForm(pokemonSaved = pokemonSaved, onSaveClick = onSaveClick)
        }
        item {
            Text(
                text = "Pokémones Guardados",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
        }
        if (pokemonList.isEmpty()) {
            item {
                Text(
                    text = "No hay pokémones guardados aún.",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        items(pokemonList) { pokemon ->
            Text(
                text = "#${pokemon.number} ${pokemon.name} - ${pokemon.type}",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun PokemonForm(
    pokemonSaved: Boolean,
    onSaveClick: (name: String, type: String, number: Int, description: String) -> Unit
) {
    var number by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val pokemonNumber = number.toIntOrNull()?.takeIf { it > 0 }
    val canSave = pokemonNumber != null && name.isNotBlank()

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = number,
            onValueChange = { if (it.all { char -> char.isDigit() }) number = it },
            label = { Text("Número") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Tipo") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (pokemonNumber != null && canSave) {
                    onSaveClick(name, type, pokemonNumber, description)
                    number = ""
                    name = ""
                    type = ""
                    description = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            enabled = canSave
        ) {
            Text("Guardar")
        }
        if (pokemonSaved) {
            Text(
                text = "¡Pokémon guardado con éxito!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
