package br.edu.ifsp.scl.sc3046664.intentscompose.ui.composable.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc3046664.intentscompose.ui.theme.IntentsComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier,
    currentString: String,
    onAddWord: () -> Unit,
    onWordReceived: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = currentString,
            label = { Text("String atual") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {},
            readOnly = true
        )

        Button(
            onClick = onAddWord,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar palavra")
        }

        Button(
            onClick = {
                onWordReceived("")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun HomeScreenLightPreview() {
    IntentsComposeTheme {
        HomeScreen(
            modifier = Modifier,
            currentString = "Olá mundo Compose",
            onAddWord = {},
            onWordReceived = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HomeScreenDarkPreview() {
    IntentsComposeTheme {
        HomeScreen(
            modifier = Modifier,
            currentString = "Olá mundo Compose",
            onAddWord = {},
            onWordReceived = {}
        )
    }
}