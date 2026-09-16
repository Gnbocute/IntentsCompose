package br.edu.ifsp.scl.sc3046664.intentscompose.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.edu.ifsp.scl.sc3046664.intentscompose.MainViewModel
import br.edu.ifsp.scl.sc3046664.intentscompose.ui.composable.screen.AddWordScreen
import br.edu.ifsp.scl.sc3046664.intentscompose.ui.composable.screen.HomeScreen

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    mainViewModel: MainViewModel
) {
    val currentString by mainViewModel
        .stringState
        .collectAsStateWithLifecycle()

    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) { backStackEntry ->

            val word by backStackEntry
                .savedStateHandle
                .getStateFlow("word", "")
                .collectAsStateWithLifecycle()

            LaunchedEffect(word) {
                if (word.isNotEmpty()) {
                    val newString = if (currentString.isEmpty()) {
                        word
                    } else {
                        "$currentString $word"
                    }

                    mainViewModel.updateString(newString)

                    backStackEntry.savedStateHandle["word"] = ""
                }
            }

            HomeScreen(
                modifier = modifier,
                currentString = currentString,
                onAddWord = {
                    navHostController.navigate(
                        Screen.AddWordScreen.route.replace(
                            "{currentString}",
                            Uri.encode(currentString)
                        )
                    )
                },
                onWordReceived = {
                    mainViewModel.updateString("")
                }
            )
        }

        composable(
            route = Screen.AddWordScreen.route,
            arguments = listOf(
                navArgument("currentString") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val currentString =
                backStackEntry.arguments?.getString("currentString") ?: ""

            AddWordScreen(
                currentString = currentString,
                modifier = modifier,
                onConcatenate = { word ->
                    navHostController
                        .previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("word", word)

                    navHostController.popBackStack()
                }
            )
        }
    }
}