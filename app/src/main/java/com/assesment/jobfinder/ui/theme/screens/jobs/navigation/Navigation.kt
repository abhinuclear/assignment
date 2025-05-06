package com.assesment.jobfinder.ui.theme.screens.jobs.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.jobfinder.ui.screens.bookmarks.BookmarksScreen
import com.example.jobfinder.ui.screens.details.JobDetailScreen
import com.example.jobfinder.ui.screens.jobs.JobsScreen

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Jobs : Screen(
        route = "jobs",
        title = "Jobs",
        selectedIcon = Icons.Filled.Work,
        unselectedIcon = Icons.Outlined.Work
    )

    object Bookmarks : Screen(
        route = "bookmarks",
        title = "Bookmarks",
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.Bookmark
    )

    object JobDetail : Screen(
        route = "job_detail/{jobId}",
        title = "Job Detail",
        selectedIcon = Icons.Filled.Work,
        unselectedIcon = Icons.Outlined.Work
    ) {
        fun createRoute(jobId: String) = "job_detail/$jobId"
    }
}

val bottomNavItems = listOf(
    Screen.Jobs,
    Screen.Bookmarks
)

fun NavGraphBuilder.jobsNavGraph(navController: NavController) {
    composable(Screen.Jobs.route) {
        JobsScreen(
            onJobClick = { jobId ->
                navController.navigate(Screen.JobDetail.createRoute(jobId))
            }
        )
    }

    composable(Screen.Bookmarks.route) {
        BookmarksScreen(
            onJobClick = { jobId ->
                navController.navigate(Screen.JobDetail.createRoute(jobId))
            }
        )
    }

    composable(
        route = Screen.JobDetail.route
    ) {
        JobDetailScreen(
            onBackClick = {
                navController.navigateUp()
            }
        )
    }
}

fun NavController.navigateSingleTopTo(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
