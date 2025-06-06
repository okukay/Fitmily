package com.ssafy.fitmily_android.presentation.navigation.bottom

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ssafy.fitmily_android.presentation.ui.main.chat.ChatScreen

fun NavGraphBuilder.chatNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "chat/main"
        , route = BottomNavItem.Chat.route
    ) {
        composable("chat/main") {
            ChatScreen(navController)
        }
    }
}