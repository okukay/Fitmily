package com.ssafy.fitmily_android.presentation.navigation.bottom

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ssafy.fitmily_android.presentation.ui.main.my.MyScreen
import com.ssafy.fitmily_android.presentation.ui.main.my.exercise.MyExerciseRegisterScreen
import com.ssafy.fitmily_android.presentation.ui.main.my.goal.MyGoalRegisterScreen
import com.ssafy.fitmily_android.presentation.ui.main.my.goal.MyGoalScreen
import com.ssafy.fitmily_android.presentation.ui.main.my.health.MyHealthRegisterScreen
import com.ssafy.fitmily_android.presentation.ui.main.my.notification.MyNotificationScreen

fun NavGraphBuilder.myNavGraph(
    parentNavController: NavHostController
    , navController: NavHostController
) {
    navigation(
        startDestination = "my/main", route = BottomNavItem.My.route
    ) {
        composable("my/main") {
            MyScreen(parentNavController, navController)
        }

        composable("my/notification") {
            MyNotificationScreen(navController)
        }

        composable("my/goal") {
            MyGoalScreen(navController)
        }

        composable("my/goal/register") {
            MyGoalRegisterScreen(navController)
        }

        composable("my/exercise") {
            MyExerciseRegisterScreen(navController)
        }

        composable("my/health") {
            MyHealthRegisterScreen(navController)
        }
    }
}