package com.sofia.mobile.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class ImagePickerViewModel : ViewModel() {
    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> get() = _selectedImageUri

    private val _launchImagePicker = MutableSharedFlow<Unit>()
    val launchImagePicker: SharedFlow<Unit> get() = _launchImagePicker

    fun selectImage(uri: Uri) {
        _selectedImageUri.value = uri
    }

    fun onCameraIconClicked() {
        _launchImagePicker.tryEmit(Unit)
    }
}