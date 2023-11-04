package com.sofia.mobile.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel(){
    private val _isShowSearchField: MutableState<Boolean> = mutableStateOf(false)
    val isShowSearchField: State<Boolean> = _isShowSearchField

    private val _currentSearchText: MutableState<String> = mutableStateOf(value = "")
    val currentSearchText: State<String> = _currentSearchText

    fun showSearchField(show: Boolean){
        _isShowSearchField.value = show
    }

    fun setCurrentSearchText(newText: String){
        _currentSearchText.value = newText
    }
}