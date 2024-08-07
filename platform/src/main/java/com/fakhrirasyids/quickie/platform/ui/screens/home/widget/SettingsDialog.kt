package com.fakhrirasyids.quickie.platform.ui.screens.home.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import com.fakhrirasyids.quickie.core.utils.LanguageUtils
import com.fakhrirasyids.quickie.platform.ui.screens.home.HomeViewModel
import com.fakhrirasyids.quickie.ui.theme.Black
import com.fakhrirasyids.quickie.ui.theme.Green
import com.fakhrirasyids.quickie.ui.theme.Red
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsDialog(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    onDismissRequest: () -> Unit,
) {
    var selectedLanguage by remember { mutableStateOf(homeViewModel.languagePreferences.value) }
    var selectedLanguageIcon by remember { mutableIntStateOf(getLanguageIcon(homeViewModel.languagePreferences.value)) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(unbounded = true)
                    .padding(top = 24.dp, bottom = 20.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Column(
                    modifier = modifier.padding(16.dp, 36.dp, 16.dp, 36.dp)
                ) {
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.settings_language_title),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxSize(),
                        fontWeight = FontWeight.ExtraBold,
                        style = TextStyle(
                            color = Color.Black
                        )
                    )

                    SpinnerLanguage(
                        modifier = modifier,
                        selectedLanguage = selectedLanguage,
                        selectedLanguageIcon = selectedLanguageIcon,
                        onLanguageSelected = { lang ->
                            selectedLanguage = lang
                            selectedLanguageIcon = getLanguageIcon(lang)
                        }
                    )
                }
            }

            Card(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .wrapContentSize(unbounded = true),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.settings_title),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            }

            Row(
                modifier = modifier
                    .align(Alignment.BottomCenter)
            ) {
                Card(
                    modifier = modifier
                        .wrapContentSize(unbounded = true)
                        .clickable {
                            onDismissRequest()
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Red,
                    )
                ) {
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.settings_dialog_cancel),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }

                Card(
                    modifier = modifier
                        .wrapContentSize(unbounded = true)
                        .padding(start = 8.dp)
                        .clickable {
                            homeViewModel
                                .setLanguagePreferences(selectedLanguage)
                                .apply {
                                    onDismissRequest()
                                }
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Black),
                    colors = CardDefaults.cardColors(
                        containerColor = Green,
                    )
                ) {
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.settings_dialog_save),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SpinnerLanguage(
    modifier: Modifier,
    selectedLanguage: String,
    selectedLanguageIcon: Int,
    onLanguageSelected: (String) -> Unit
) {
    var expandedLanguageSpinner by remember { mutableStateOf(false) }

    val languageArrayResource = LanguageUtils.Language.getLanguagesList()

    var spinnerLanguageSize by remember {
        mutableStateOf(Size.Zero)
    }

    val dropdownIcon = if (expandedLanguageSpinner) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = modifier
            .padding(top = 8.dp)
    ) {
        OutlinedTextField(
            value = selectedLanguage,
            onValueChange = { onLanguageSelected.invoke(it) },
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    spinnerLanguageSize = coordinates.size.toSize()
                }
                .clickable {
                    expandedLanguageSpinner = !expandedLanguageSpinner
                },
            trailingIcon = {
                Icon(imageVector = dropdownIcon, contentDescription = "Language Dropdown Icon")
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = selectedLanguageIcon),
                    contentDescription = "Language Dropdown Icon"
                )
            },
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = Color.Transparent,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
        )

        DropdownMenu(
            expanded = expandedLanguageSpinner,
            onDismissRequest = { expandedLanguageSpinner = false },
            modifier = modifier
                .width(with(LocalDensity.current) {
                    spinnerLanguageSize.width.toDp()
                })
        ) {
            languageArrayResource.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Row {
                            Image(
                                painter = painterResource(id = getLanguageIcon(label)),
                                contentDescription = "Language Dropdown Icon"
                            )
                            Text(
                                modifier = modifier.padding(start = 8.dp),
                                text = label,
                                fontSize = 16.sp
                            )
                        }
                    },
                    onClick = {
                        onLanguageSelected.invoke(label)
                        expandedLanguageSpinner = false
                    }
                )
            }
        }
    }
}

fun getLanguageIcon(lang: String) = when (lang) {
    LanguageUtils.Language.INDONESIA.toString() -> com.fakhrirasyids.quickie.baseResource.R.drawable.ic_flag_id_64
    LanguageUtils.Language.JAPANESE.toString() -> com.fakhrirasyids.quickie.baseResource.R.drawable.ic_flag_ja_64
    LanguageUtils.Language.CHINESE.toString() -> com.fakhrirasyids.quickie.baseResource.R.drawable.ic_flag_zh_64
    LanguageUtils.Language.ARABIC.toString() -> com.fakhrirasyids.quickie.baseResource.R.drawable.ic_flag_ar_64
    else -> com.fakhrirasyids.quickie.baseResource.R.drawable.ic_flag_uk_64
}

@Preview(showBackground = true)
@Composable
fun SettingsDialogPreview() {
    SettingsDialog(onDismissRequest = {}, homeViewModel = koinViewModel())
}
