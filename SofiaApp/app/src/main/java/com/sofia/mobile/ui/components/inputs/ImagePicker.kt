package com.sofia.mobile.ui.components.inputs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.Lilas

@Composable
fun ImagePicker(){
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty { R.drawable.ic_cute_star }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Box(
        modifier = Modifier.size(92.dp),
        contentAlignment = Alignment.Center
    ){
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
                //.offset(y = 2.dp) // Move the box down by half its height
        ){
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(25.dp)
                    .clickable { launcher.launch("image/*") },
                painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                contentDescription = "Icon camera picker"
            )
        }
    }
}


@Preview
@Composable
fun ImagePickerPreview(){
    ImagePicker()
}