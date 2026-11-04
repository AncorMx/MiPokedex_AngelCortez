package cortez.angel.pokedexlist.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import cortez.angel.pokedexlist.data.preferences.ARCHIVO
import cortez.angel.pokedexlist.data.preferences.POKEMON_DISPLAY
import cortez.angel.pokedexlist.data.preferences.USER
import cortez.angel.pokedexlist.data.preferences.USERNAME
import cortez.angel.pokedexlist.domain.User

/**
 * Manejador de usuarios y preferencias locales utilizando SharedPreferences.
 * Administra el inicio de sesión y la configuración de visualización de la interfaz.
 */
class Users(context: Context) {

    // Simulación de base de datos de usuarios locales
    private val userList = listOf(
        User("angel@pokedex.com", "pokedex123", "Angel Cortez")
    )

    private val shared: SharedPreferences =
        context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    /**
     * Busca un usuario por su dirección de email ignorando mayúsculas/minúsculas.
     */
    fun getUserByMail(mail: String): User? =
        userList.find { it.email.equals(mail.trim(), ignoreCase = true) }

    /**
     * Valida credenciales. Si son correctas, almacena la sesión localmente.
     */
    fun loginService(email: String, password: String): User? {
        val user = getUserByMail(email)?.takeIf { it.password == password } ?: return null
        saveUser(user.email, user.name)
        return user
    }

    fun saveUser(email: String, name: String) {
        // Almacena en memoria de forma persistente
        shared.edit {
            putString(USER, email)
            putString(USERNAME, name)
        }
    }

    /** Verifica si existe una sesión activa actualmente en el dispositivo. */
    fun isLoggedIn(): Boolean =
        shared.getString(USER, null) != null && shared.getString(USERNAME, null) != null

    /** Cierra la sesión actual eliminando los datos del usuario. Mantiene otras preferencias intactas. */
    fun logout() {
        shared.edit {
            remove(USER)
            remove(USERNAME)
        }
    }

    /** Obtiene el modo de visualización preferido (lista o cuadrícula). */
    fun isGridDisplay(): Boolean = shared.getBoolean(POKEMON_DISPLAY, false)

    fun saveGridDisplay(grid: Boolean) {
        shared.edit { putBoolean(POKEMON_DISPLAY, grid) }
    }
}
