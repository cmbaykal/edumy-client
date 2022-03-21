package com.baykal.edumyclient.ui.screen.questionSection.questionDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EImage
import com.baykal.edumyclient.base.component.ETextButton
import com.baykal.edumyclient.base.component.RowLayout
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute

@Composable
fun QuestionDetailScreen(
    viewModel: QuestionDetailViewModel
) {
    val context = LocalContext.current
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    with(viewState) {
        LaunchedEffect(question) {
            if (question == null)
                viewModel.fetchData()
        }

        question?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RowLayout(label = "Lesson", value = it.lesson.toString())
                RowLayout(label = "User", value = it.user?.name.toString()) {
                    it.user?.id?.let { userId ->
                        viewModel.navigate(ProfileRoute.get(userId))
                    }
                }
                RowLayout(label = "Description", value = it.description.toString())
                it.image?.let { image ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.weight(2f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Image"
                        )
                        EImage(
                            modifier = Modifier
                                .weight(4f),
                            file = image
                        )
                    }
                }
                ETextButton(text = "Answers") {
                    // TODO: Navigate to question answers
                }
            }
        }
    }
}