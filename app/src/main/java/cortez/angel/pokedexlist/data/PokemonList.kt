package cortez.angel.pokedexlist.data

import cortez.angel.pokedexlist.R
import cortez.angel.pokedexlist.domain.Pokemon

val bulbasur = Pokemon(
    "Bulbasaur",
    1,
    "Planta/Veneno",
    "Lleva una semilla en la espalda desde que nace, la cual crece gradualmente con él.",
    0.7f,
    6.9f,
    false,
    "Espesura",
    R.drawable.bulbasaur
)

val pokemonList = listOf(
    Pokemon(
        "Bulbasaur",
        1,
        "Planta/Veneno",
        "Lleva una semilla en la espalda desde que nace, la cual crece gradualmente con él.",
        0.7f,
        6.9f,
        false,
        "Espesura",
        R.drawable.bulbasaur
    ),
    Pokemon("Charmander", 4, "Fuego", "La llama en su cola indica la fuerza de su vida. Arde con más fuerza si está sano.", 0.6f, 8.5f, true, "Mar Llamas", R.drawable.charmander),
    Pokemon("Squirtle", 7, "Agua", "Tras nacer, su lomo se hincha y se endurece formando un resistente caparazón.", 0.5f, 9.0f, false, "Torrente", R.drawable.squirtle),
    Pokemon("Pikachu", 25, "Eléctrico", "Cuando se reúnen varios de estos Pokémon, su electricidad puede acumularse y provocar tormentas.", 0.4f, 6.0f, true, "Electricidad Estática", R.drawable.pikachu),
    Pokemon("Jigglypuff", 39, "Normal/Hada", "Atrapa a sus enemigos con sus grandes ojos y los adormece cantando una melodía agradable.", 0.5f, 5.5f, false, "Gran Encanto", R.drawable.jigglypuff),
    Pokemon("Gengar", 94, "Fantasma/Veneno", "Bajo la luz de la luna llena, le gusta imitar las sombras de la gente y reírse de sus sustos.", 1.5f, 40.5f, true, "Cuerpo Maldito", R.drawable.gengar),
    Pokemon("Snorlax", 143, "Normal", "Su estómago es tan fuerte que puede digerir incluso comida mohosa o podrida sin sufrir daño.", 2.1f, 460.0f, false, "Inmunidad", R.drawable.snorlax),
    Pokemon("Mewtwo", 150, "Psíquico", "Fue creado por un científico tras años de horribles experimentos genéticos e ingeniería de ADN.", 2.0f, 122.0f, true, "Presión", R.drawable.mewtwo),
    Pokemon("Lucario", 448, "Lucha/Acero", "Al detectar el aura que emiten los demás, puede leer sus pensamientos y anticipar sus movimientos.", 1.2f, 54.0f, true, "Impasible", R.drawable.lucario),
    Pokemon("Mimikyu", 778, "Fantasma/Hada", "Su apariencia real es desconocida. Un investigador que vio debajo de su disfraz murió de terror.", 0.2f, 0.7f, false, "Disfraz", R.drawable.mimikyu)
)
