import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val colors = if (isSystemInDarkTheme()) lightScheme else darkScheme
    MaterialTheme(colorScheme = colors) {
        AddLoginScreen()

    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AddLoginScreen() {
    var username by rememberSaveable { mutableStateOf("") }
    var pasword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .layout { measurable, constraints ->
                val maxWidth = constraints.maxWidth
                val childConstraints = constraints.copy(
                    minWidth = 0,
                    maxWidth = maxWidth.coerceAtMost(800) // Ancho máximo de 400dp
                )
                val placeable = measurable.measure(childConstraints)
                layout(placeable.width, placeable.height) {
                    placeable.place(0, 0)
                }
            },

        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "LOGIN",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "User",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Enter your username") },
        )

        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pasword,
            onValueChange = { pasword = it },
            placeholder = { Text(text = "Enter your password") },
        )
    }
}