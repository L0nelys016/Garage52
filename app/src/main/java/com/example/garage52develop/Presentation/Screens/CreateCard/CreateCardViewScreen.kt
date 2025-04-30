package com.example.garage52develop.Presentation.Screens.CreateCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes


@Composable
fun CreateCardViewScreen(navController: NavController, viewModel: CreateCardViewModel = viewModel()){

    val categories = viewModel.categories.observeAsState(emptyList())
    val selectedCategory = remember { mutableStateOf("") }

    val detailState = viewModel.detailState

    Box(

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                10.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier.padding(
                end = 10.dp,
                start = 10.dp,
                bottom = 15.dp,
                top = 50.dp
            )
        )
        {
            TextFieldAdd(text = detailState.title, label = "Название детали",
                onValueChanged = { viewModel.updateDetail(detailState.copy(title = it)) })
            TextFieldAdd(text = detailState.description, label = "Описание",
                onValueChanged = { viewModel.updateDetail(detailState.copy(description = it)) })
            TextFieldCategoriesAdd(text = selectedCategory.value)
            LazyRow {
                items(categories.value.indices.toList()) { index ->
                    CatregoriesButton(
                        typeCategories = categories.value[index].copy(),
                        onClick = {
                            selectedCategory.value = categories.value[index].id
                            viewModel.updateDetail(detailState.copy(categoryId = selectedCategory.value))
                        }
                    )
                }
            }
            MoreDetailedButtonAdd(text = "Добавить", onClick = {viewModel.addDetail()
                navController.navigate(NavigationRoutes.MAIN) })
        }
    }
}