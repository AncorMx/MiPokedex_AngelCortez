package cortez.angel.pokedexlist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cortez.angel.pokedexlist.data.Users

// Mensaje de error predeterminado
const val LOGIN_ERROR = "Credenciales incorrectas. Verifica tu email y contraseña."

// ViewModel encargado de administrar la sesión y las preferencias de visualización del usuario
class UserViewModel(private val users: Users) : ViewModel() {

    // Estado que determina si hubo un error en el inicio de sesión
    var loginError by mutableStateOf(false)
        private set

    var errorDescription by mutableStateOf("")
        private set

    // Modo de visualización actual de los Pokemon
    var gridDisplay by mutableStateOf(users.isGridDisplay())
        private set

    // Ejecuta el intento de autenticación y maneja los estados en consecuencia
    fun login(mail: String, password: String) {
        val user = users.loginService(mail, password)
        if (user == null) {
            loginError = true
            errorDescription = LOGIN_ERROR
        } else {
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean = users.isLoggedIn()

    fun logout() {
        users.logout()
    }

    fun changeDisplay(grid: Boolean) {
        gridDisplay = grid
        users.saveGridDisplay(grid)
    }

    companion object {
        // Factory del ViewModel que provee las dependencias utilizando el contexto
        val Factory = viewModelFactory {
            initializer { UserViewModel(Users(checkNotNull(this[APPLICATION_KEY]))) }
        }
    }
}
