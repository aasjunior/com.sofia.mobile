package com.sofia.mobile.ui.view.contents.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import com.sofia.mobile.R
import com.sofia.mobile.ui.theme.SofiaColorScheme.BrillantPurple
import com.sofia.mobile.ui.theme.SofiaColorScheme.Lilas
import com.sofia.mobile.ui.theme.SofiaTypography.text12

@Composable
fun SearchableTopBar(
    isShowSearchField: Boolean,
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onSearchIconClicked: () -> Unit
){
    when(isShowSearchField){
        true -> SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDeactivated = onSearchDeactivated,
            onSearchDispatched = onSearchDispatched
        )
        false -> IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        }
    }
}

@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit
){
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            ),
        value = currentSearchText,
        onValueChange = { onSearchTextChanged(it) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 12.sp, color = Lilas),
        cursorBrush = SolidColor(Color.Gray),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchDispatched(currentSearchText)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchLeadingIcon()
                Box(Modifier.weight(1f)) {
                    if (currentSearchText.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.form_search),
                            style = text12.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
                SearchTrailingIcon {
                    if (currentSearchText.isNotEmpty())
                        onSearchTextChanged("")
                    else
                        onSearchDeactivated()
                }
            }
        }
    )
}

@Composable
private fun SearchIcon(action: () -> Unit = {}){
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescription = "Search Icon",
        onIconClickAction = action
    )
}

@Composable
private fun SearchLeadingIcon(action: () -> Unit = {}){
    DefaultIcon(
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .height(16.dp),
        onIconClickAction = action
    )
}

@Composable
private fun SearchTrailingIcon(action: () -> Unit = {}){
    DefaultIcon(
        modifier = Modifier.height(16.dp),
        searchIcon = Icons.Default.Close,
        contentDescription = "Deactivate Search Icon",
        onIconClickAction = action
    )
}

@Composable
private fun DefaultIcon(
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Default.Search,
    iconColor: Color = BrillantPurple,
    contentDescription: String = "Magnifier Search Icon",
    onIconClickAction: () -> Unit = {}
){
    IconButton(
        modifier = modifier,
        onClick = onIconClickAction
    ){
        Icon(
            imageVector = searchIcon,
            contentDescription = contentDescription,
        )
    }
}

@Composable
@Preview
private fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {}
    )
}