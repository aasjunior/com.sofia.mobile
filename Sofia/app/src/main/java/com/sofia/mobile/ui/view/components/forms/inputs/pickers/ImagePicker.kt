package com.sofia.mobile.ui.view.components.forms.inputs.pickers

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.sofia.mobile.R
import com.sofia.mobile.ui.viewmodel.ImagePickerViewModel

@Composable
fun ImagePicker(vm: ImagePickerViewModel, onPickImageClick: () -> Unit) {
    val imageUri by vm.selectedImageUri.collectAsState()

    val context = LocalContext.current

    val painter = imageUri?.let { uri ->
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                .data(uri.toString())
                .size(Size.ORIGINAL)
                .build()
        )
    } ?: painterResource(id = R.drawable.ic_cute_icon)

    Box(
        modifier = Modifier.size(92.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(92.dp),
            painter = painter,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(Color.Transparent)
                .size(25.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(25.dp)
                    .clickable { onPickImageClick() },
                painter = painterResource(id = R.drawable.ic_camera_picker),
                contentDescription = "Icon camera picker"
            )
        }
    }
}

