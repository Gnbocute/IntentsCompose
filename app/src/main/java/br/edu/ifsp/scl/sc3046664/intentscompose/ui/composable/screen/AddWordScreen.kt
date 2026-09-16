package br.edu.ifsp.scl.sc3046664.intentscompose.ui.composable.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc3046664.intentscompose.ui.theme.IntentsComposeTheme

@Composable
fun AddWordScreen(
    currentString: String,
    modifier: Modifier,
    onConcatenate: (String) -> Unit
) {
    var word by remember { mutableStateOf("") }

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

        OutlinedTextField(
            value = word,
            label = { Text("Nova palavra") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { word = it }
        )

        Button(
            onClick = {
                onConcatenate(word)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Concatenar")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AddWordScreenLightPreview() {
    IntentsComposeTheme {
        AddWordScreen(
            currentString = "Olá mundo",
            modifier = Modifier,
            onConcatenate = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AddWordScreenDarkPreview() {
    IntentsComposeTheme {
        AddWordScreen(
            currentString = "Olá mundo",
            modifier = Modifier,
            onConcatenate = {}
        )
    }
}