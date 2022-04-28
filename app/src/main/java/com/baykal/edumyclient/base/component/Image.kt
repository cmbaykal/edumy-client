package com.baykal.edumyclient.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baykal.edumyclient.R
import com.baykal.edumyclient.di.BASE_URL

@Composable
fun EImage(
    modifier: Modifier = Modifier,
    file: String,
    description: String = "",
) {
    val context = LocalContext.current
    var dialogState by remember { mutableStateOf(false) }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data("$BASE_URL/image/$file")
            .crossfade(true)
            .build(),
        contentDescription = description,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .then(modifier)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)))
            .clickable {
                dialogState = true
            }
    )

    if (dialogState) {
        ImageDialog(file = file, description = description) {
            dialogState = false
        }
    }
}