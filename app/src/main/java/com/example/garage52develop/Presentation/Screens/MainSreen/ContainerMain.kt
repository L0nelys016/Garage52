package com.example.garage52develop.Presentation.Screens.MainSreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garage52develop.Domain.models.Categories

@Composable
fun ContainerMain(
    txt: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = txt,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            cursorColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Black
        ),
        label = {
            Text(text = label)
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(vertical = 50.dp),
        maxLines = 1,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun CatregoriesButton(typeCategories: Categories?, onClick: () -> Unit){
    Box(
        modifier = Modifier.padding(10.dp)
    ){
    Box(
        modifier = Modifier.
        border(
            width = 2.dp,
            color = Color(0xFF000000),
            shape = RoundedCornerShape(8.dp)
        )
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            .padding(10.dp)
            .width(100.dp),
        contentAlignment = Alignment.Center,
    ){
        Text(text = typeCategories?.name ?: "", color = Color(0xFF000000), textAlign = TextAlign.Center)
    }
    }
}

@Composable
fun CreateButton(onClick: () -> Unit){
    Box(
        modifier = Modifier.
        border(
            width = 2.dp,
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
            .padding(10.dp)
            .width(100.dp)
            .height(36.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,

        ){
        Text(text = "Добавить", color = Color.Black, fontWeight = FontWeight(1000), fontSize = 18.sp)
    }
}
