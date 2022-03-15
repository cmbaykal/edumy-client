package com.baykal.edumyclient.ui.screen.questionSection.askquestion

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.util.MediaUtil
import com.baykal.edumyclient.data.model.classroom.Lesson

@Composable
fun AskQuestionScreen(
    viewModel: AskQuestionViewModel
) {
    val context = LocalContext.current
    val viewState = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val contentPickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewModel.setImage(it)
        }
    }

    val capturePhotoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaUtil.getBitmapUri(context, bitmap)
        } else {
            MediaUtil.legacySave(context, bitmap)
        }
        uri?.let {
            viewModel.setImage(it)
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            capturePhotoLauncher.launch()
        }
    }

    val lessonItems = mutableListOf<String>()
    enumValues<Lesson>().forEach {
        lessonItems.add(it.lessonName)
    }

    with(viewState.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(25.dp)
        ) {
            EDropDown(
                label = "Lesson",
                items = lessonItems,
                selected = lesson.text,
                onChange = viewModel::setLesson
            )
            ETextField(
                label = "Description",
                value = description.text,
                onChange = viewModel::setDescription,
                success = { it.isNotBlank() },
                maxLines = 3
            )
            imageUri?.let {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(it)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ECheckbox(
                    modifier = Modifier.weight(8f),
                    label = "Ask anonymously",
                    onChecked = {
                        viewModel.setAnonymous(it)
                    }
                )
                EIconButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.CameraAlt,
                    onClick = {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    })
                EIconButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.Photo,
                    onClick = {
                        contentPickerLauncher.launch("image/*")
                    })
            }
            EButton(
                text = "Send Question"
            ) {
                viewModel.sendQuestion()
            }
        }
    }
}