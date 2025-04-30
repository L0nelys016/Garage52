package com.example.garage52develop.Presentation.Screens.MainSreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.garage52develop.Domain.State.ResultState
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes
import com.example.garage52develop.Presentation.Screens.SigInScreen.SignInScreen

@Composable
fun MainScreen(
    navController: NavController,
    mainView: MainScreenViewModel = viewModel()
) {
    val timeState by mainView.resultState.collectAsState()
    val detail = mainView.detail.observeAsState(emptyList())
    val categories = mainView.categories.observeAsState(emptyList())
    val txtSearch = remember { mutableStateOf("") }
    val CategorySelect = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(vertical = 50.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Поле поиска

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = txtSearch.value,
                onValueChange = { newText ->
                    txtSearch.value = newText
                    mainView.filterListDetail(newText, CategorySelect.value)
                },
                label = { Text("Поиск", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Поиск",
                        tint = Color.Black
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Black
                ),
                modifier = Modifier
                    .weight(1f)
                    .border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { focusManager.clearFocus() }
                )
            )
            CreateButton(onClick = {navController.navigate(NavigationRoutes.CREATECARD) })
        }

        Spacer(modifier = Modifier.height(24.dp))


        when (timeState) {
            is ResultState.Error ->
                Text(
                    text = (timeState as ResultState.Error).message,
                    color = Color.Black
                )

            ResultState.Initalized -> TODO()
            is ResultState.Loading -> {
                Box(
                    modifier = Modifier.size(100.dp)
                ) {
                    LinearProgressIndicator(color = Color.Black)
                }
            }

            is ResultState.Success -> {
                LazyRow {
                    items(categories.value.indices.toList()) { index ->
                        CatregoriesButton(typeCategories = categories.value[index].copy(),
                            onClick = {
                                CategorySelect.value = categories.value[index].id
                                mainView.filterListDetail(
                                    txtSearch.value,
                                    categoryId = CategorySelect.value
                                )
                            }
                        )
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(detail.value) { item ->
                        Column {
                            DetailCard(
                                detail = item,
                                modifier = Modifier.padding(vertical = 12.dp),
                                onClick = {}
                            )
                            Divider(
                                color = Color.Black,
                                thickness = 3.dp,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
