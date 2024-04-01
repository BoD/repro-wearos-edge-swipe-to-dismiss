package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.WEAR_OS_SMALL_ROUND
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.foundation.SwipeToDismissBoxState
import androidx.wear.compose.foundation.edgeSwipeToDismiss
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavHostState
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.pager.PagerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    val navController = rememberSwipeDismissableNavController()
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
    val swipeDismissableNavHostState = rememberSwipeDismissableNavHostState(swipeToDismissBoxState)
    MyApplicationTheme {
        AppScaffold {
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = "main",
                state = swipeDismissableNavHostState,
            ) {
                composable("main") {
                    MainScreen(
                        swipeToDismissBoxState = swipeToDismissBoxState,
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(swipeToDismissBoxState: SwipeToDismissBoxState) {
    val pagerState: PagerState = rememberPagerState(pageCount = { 3 })
    PagerScreen(
        modifier = Modifier
            .fillMaxSize()
            .edgeSwipeToDismiss(swipeToDismissBoxState),
        state = pagerState
    ) { page ->
        Text(
            text = "Page $page",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.title1
        )
    }
}

@Preview(device = WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}
