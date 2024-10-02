package com.sha.starzpaly.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.sha.playdata.data.models.Media
import com.sha.starzpaly.presentation.detail.DetailScreen
import com.sha.starzpaly.presentation.main.MainScreen
import com.sha.starzpaly.presentation.player.MediaPlayerScreen
import com.sha.starzpaly.splash.SplashScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.Splash.route

) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination,

        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }

    ) {
        splashGraph(navController)
        mainGraph(navController)
        detailGraph(navController)
        mediaPlayerGraph(navController)

    }
}


fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(Destinations.Splash.route) {
        SplashScreen(modifier = Modifier.fillMaxSize(),
            goToMainScreen = {
                navController.navigate(Destinations.Main.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        )
    }
}


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable(Destinations.Main.route) {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            goToDetailScree = { media ->
                navController.navigate(Destinations.Detail.createRoute(media))
            }
        )
    }
}

fun NavGraphBuilder.detailGraph(navController: NavController) {
    composable(
        Destinations.Detail.route,
        arguments = listOf(navArgument("mediaJson") { type = NavType.StringType })
    ) { backStackEntry ->
        val mediaJson = backStackEntry.arguments?.getString("mediaJson")
        val decodedMediaJson = URLDecoder.decode(mediaJson, StandardCharsets.UTF_8.toString())
        val media: Media = Gson().fromJson(decodedMediaJson, Media::class.java) // Deserialize back to Media
        DetailScreen(modifier = Modifier.fillMaxSize(), media = media, goToPlayer = {
            navController.navigate(Destinations.MediaPlayer.createRoute(media))
        })
    }
}

fun NavGraphBuilder.mediaPlayerGraph(navController: NavController) {
    composable(
        Destinations.MediaPlayer.route,
        arguments = listOf(navArgument("mediaJson") { type = NavType.StringType })
    ) { backStackEntry ->
        val mediaJson = backStackEntry.arguments?.getString("mediaJson")
        val decodedMediaJson = URLDecoder.decode(mediaJson, StandardCharsets.UTF_8.toString())
        val media: Media = Gson().fromJson(decodedMediaJson, Media::class.java)
        MediaPlayerScreen(modifier = Modifier.fillMaxSize(), media = media)
    }
}




