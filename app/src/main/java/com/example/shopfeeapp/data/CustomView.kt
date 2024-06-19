package com.example.shopfeeapp.data

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextBold(title:String, modifier: Modifier = Modifier){
    Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
}