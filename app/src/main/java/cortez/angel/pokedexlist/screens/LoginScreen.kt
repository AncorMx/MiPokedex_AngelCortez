package cortez.angel.pokedexlist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cortez.angel.pokedexlist.R
import cortez.angel.pokedexlist.viewModel.LOGIN_ERROR

private const val SELECTION_ALPHA = 0.4f

@Composable
fun LoginScreen(
    isLoggedIn: Boolean,
    loginError: Boolean,
    errorDescription: String,
    onLoginClick: (mail: String, password: String) -> Unit,
    onLoggedIn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isLoggedIn) {
        LaunchedEffect(Unit) { onLoggedIn() }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.weight(1f))
            LoginHeader()
            Spacer(Modifier.weight(1f))
            // Replaced PokedexCard with simple box to match project components if it doesn't exist
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 32.dp)
            ) {
                LoginForm(
                    loginError = loginError,
                    errorDescription = errorDescription,
                    onLoginClick = onLoginClick,
                    modifier = Modifier
                        .widthIn(max = 400.dp)
                        .padding(24.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}

@Composable
private fun LoginHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
        )
        Text(
            text = "MiPokedex",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

@Composable
private fun LoginForm(
    loginError: Boolean,
    errorDescription: String,
    onLoginClick: (mail: String, password: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    
    val canLogin = email.isNotBlank() && password.isNotEmpty()
    val focusManager = LocalFocusManager.current
    
    val login = {
        focusManager.clearFocus()
        onLoginClick(email, password)
    }
    
    val fieldColors = loginFieldColors()
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo electrónico") },
            isError = loginError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next,
            ),
            singleLine = true,
            colors = fieldColors,
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            trailingIcon = {
                PasswordVisibilityButton(
                    visible = passwordVisible,
                    onClick = { passwordVisible = !passwordVisible },
                )
            },
            isError = loginError,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            singleLine = true,
            colors = fieldColors,
        )
        Button(
            onClick = login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = canLogin,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            ),
        ) {
            Text("Iniciar sesión")
        }
        if (loginError) {
            Text(
                text = errorDescription,
                color = MaterialTheme.colorScheme.error,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun PasswordVisibilityButton(visible: Boolean, onClick: () -> Unit) {
    // using generic icons if specific drawables not available
    IconButton(onClick = onClick) {
        Text(text = if (visible) "Ocultar" else "Mostrar")
    }
}

@Composable
private fun loginFieldColors(): TextFieldColors {
    val focused = MaterialTheme.colorScheme.onSurface
    return OutlinedTextFieldDefaults.colors(
        cursorColor = focused,
        selectionColors = TextSelectionColors(
            handleColor = focused,
            backgroundColor = focused.copy(alpha = SELECTION_ALPHA),
        ),
        focusedBorderColor = focused,
        focusedLabelColor = focused,
    )
}
