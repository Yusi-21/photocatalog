package com.success.photocatalog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.success.photocatalog.presentation.ui.SharedViewModel
import com.success.photocatalog.presentation.ui.detail.PhotoDetailScreen
import com.success.photocatalog.presentation.ui.list.PhotoListScreen

sealed class Screen(val route: String) {
    object List : Screen("photo_list")
    object Detail : Screen("photo_detail")
}

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.List.route,
        modifier = modifier
    ) {
        composable(Screen.List.route) {
            PhotoListScreen(
                onPhotoClick = { photo ->
                    sharedViewModel.selectedPhoto = photo
                    navController.navigate(Screen.Detail.route)
                }
            )
        }

        composable(Screen.Detail.route) {
            val photo = sharedViewModel.selectedPhoto
            if (photo != null) {
                PhotoDetailScreen(photo = photo)
            }
        }
    }
}