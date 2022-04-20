package com.baykal.edumyclient.ui.screen.questionSection.sendAnswer

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
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ECheckbox
import com.baykal.edumyclient.base.component.EIconButton
import com.baykal.edumyclient.base.component.ETextField
import com.baykal.edumyclient.base.util.MediaUtil

@Composable
fun WriteAnswerScreen(
    viewModel: WriteAnswerViewModel
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

    with(viewState) {
        LaunchedEffect(questionId) {
            if (questionId == null)
                viewModel.fetchData()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(25.dp)
        ) {
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
                    label = stringResource(id = R.string.send_anonymously_text),
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
                EIconButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.Videocam,
                    onClick = {
                        contentPickerLauncher.launch("video/*")
                    })
            }
            EButton(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                text = stringResource(id = R.string.send_answer_button)
            ) {
                viewModel.sendAnswer()
            }
        }
    }
}