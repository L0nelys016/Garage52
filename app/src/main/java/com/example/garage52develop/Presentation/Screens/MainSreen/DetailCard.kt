package com.example.garage52develop.Presentation.Screens.MainSreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.garage52develop.Domain.models.Detail
import com.example.garage52develop.R

@Composable
fun DetailCard(detail: Detail, modifier: Modifier, onClick: () -> Unit){
    Box(
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically))
        {
            Text(
                text = detail.title,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            AsyncImage(
                model = detail.image,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
                    .clickable (onClick = onClick),
                error = painterResource(R.drawable.garage52),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = detail.description,
                fontSize = 15.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}