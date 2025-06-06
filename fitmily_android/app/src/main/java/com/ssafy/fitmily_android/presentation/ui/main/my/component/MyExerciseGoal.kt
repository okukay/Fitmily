package com.ssafy.fitmily_android.presentation.ui.main.my.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ssafy.fitmily_android.model.dto.response.home.GoalDto
import com.ssafy.fitmily_android.model.dto.response.my.MyGoalDto
import com.ssafy.fitmily_android.ui.theme.Typography
import com.ssafy.fitmily_android.ui.theme.mainBlack
import com.ssafy.fitmily_android.ui.theme.mainWhite

@Composable
fun MyExerciseGoal(
    goals: List<MyGoalDto>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(mainWhite, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "목표",
            color = mainBlack,
            style = Typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        goals.forEach { goal ->
            GoalProgressItem(goal)
            Spacer(Modifier.height(8.dp))
        }
    }
}