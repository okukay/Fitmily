package com.ssafy.fitmily_android.presentation.ui.main.my.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ssafy.fitmily_android.presentation.ui.components.EmptyContentText
import com.ssafy.fitmily_android.presentation.ui.main.my.notification.component.MyNotificationItem
import com.ssafy.fitmily_android.presentation.ui.main.my.notification.component.MyNotificationTopBar
import com.ssafy.fitmily_android.ui.theme.backGroundGray
import io.reactivex.Notification

@Composable
fun MyNotificationScreen (
    navController: NavHostController
    , myNotificationViewModel: MyNotificationViewModel = hiltViewModel()
) {
    // TODO: delete
//    val myNotifications = listOf<Notification>()
//    val myNotifications = listOf(
//        Notification("STING", "2025.01.20", "수미동산님이 콕 찔렀습니다!", "")
//        , Notification("CHALLENGE", "2025.01.20", "05.10 ~ 05.17 가족 챌린지가 시작되었습니다!", "산책을 진행하여 등수를 높여보세요!",)
//        , Notification("WALK", "2025.01.20", "수미동산님이 산책을 시작했어요!", "열심히 산책하고 있는지 지켜볼까요?")
//    )

    val myNotifications by myNotificationViewModel.notifications

    LaunchedEffect (Unit) {
        myNotificationViewModel.getNotifications()
    }

    if (myNotifications.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backGroundGray)
        ) {
            item {
                MyNotificationTopBar(navController)
            }

            item {
                Spacer(
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }

            items (myNotifications) { item ->
                MyNotificationItem(item, navController)

                Spacer(
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    } else {
        MyNotificationTopBar(
            navController = navController
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
            , contentAlignment = Alignment.Center
        ) {
            EmptyContentText(
                "받은 알림이 없어요", "알림을 켜고 소식을 받아보세요"
            )
        }
    }
}

data class Notification(
    val type: String
    , val date: String
    , val title: String
    , val content: String
)