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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.util.MediaUtil
import com.baykal.edumyclient.data.model.classroom.Lesson

@Composable
fun AskQuestionScreen(
    viewModel: AskQuestionViewModel
) {
    val context = LocalContext.current
    val viewState by viewModel.uiState.collectAsState()
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

    val lessonItems = Lesson.values().map {
        it.lessonName
    }.toMutableList()

    with(viewState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_huge),
                    end = dimensionResource(id = R.dimen.padding_huge),
                    bottom = dimensionResource(id = R.dimen.padding_huge)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            EDropDown(
                label = stringResource(id = R.string.lesson_hint),
                items = lessonItems,
                selected = lesson.text,
                onChange = viewModel::setLesson
            )
            ETextField(
                label = stringResource(id = R.string.description_hint),
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
                        .padding(dimensionResource(id = R.dimen.padding_standard))
                        .height(dimensionResource(id = R.dimen.image_height_standard))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)))
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ECheckbox(
                    modifier = Modifier.weight(8f),
                    label = stringResource(id = R.string.ask_anonymously_text),
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
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.button_height_standard)),
                text = stringResource(id = R.string.send_question_button)
            ) {
                viewModel.sendQuestion()
            }
        }
    }
}