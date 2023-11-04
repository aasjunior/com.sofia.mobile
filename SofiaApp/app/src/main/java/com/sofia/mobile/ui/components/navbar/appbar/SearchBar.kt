package com.sofia.mobile.ui.components.navbar.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ContentAlpha
import com.sofia.mobile.ui.theme.Lilas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.sp
import com.sofia.mobile.ui.theme.BrillantPurple

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit
){
    BasicTextField(
        modifier = Modifier
            .width(234.dp)
            .height(31.dp)
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
                    .height(31.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchLeadingIcon()
                Box(Modifier.weight(1f)) {
                    if (currentSearchText.isEmpty()) {
                        Text(
                            text = "Pesquise por algo",
                            style = TextStyle(color = Lilas)
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


// Criação dos icones de busca partindo do icone padrão
@Composable
fun SearchIcon(action: () -> Unit = {}){
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescription = "Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun SearchLeadingIcon(action: () -> Unit = {}){
    DefaultIcon(
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .height(16.dp),
        onIconClickAction = action
    )
}

@Composable
fun SearchTrailingIcon(action: () -> Unit = {}){
    DefaultIcon(
        modifier = Modifier.height(16.dp),
        searchIcon = Icons.Default.Close,
        contentDescription = "Deactivate Search Icon",
        onIconClickAction = action
    )
}

// Icone padrão reutilizável
@Composable
fun DefaultIcon(
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
fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {}
    )
}