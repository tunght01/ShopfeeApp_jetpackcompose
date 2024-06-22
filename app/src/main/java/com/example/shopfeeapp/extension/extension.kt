package com.example.shopfeeapp.extension

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.shopfeeapp.datastore.StoreUserEmail
import com.example.shopfeeapp.model.FomartUtility

@Composable
fun HeaderIconBack(title:String, onClickBack:()->Unit){
    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        IconButton(onClick = { onClickBack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
        Text(text = title)
    }


}
@Composable
fun textFileWhite(title: String,modifier: Modifier=Modifier){
    Text(text = title, modifier = Modifier, style = TextStyle(color = Color.White))
}
@Composable
fun textFileWhiteBold(title: String,modifier: Modifier=Modifier){
    Text(text = title, modifier = Modifier, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
}
@Composable
fun FooterFixed(price:Float, textButton:String, onClickButton:()->Unit
){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .background(
            color = Color("#F4EFEB".toColorInt()),
            shape = RoundedCornerShape(5.dp)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Column (modifier = Modifier
            .padding(10.dp)){
            Text(text = "Tổng tiền")
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = FomartUtility().format(price = price.toInt()),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        Button(onClick = { onClickButton() }, modifier = Modifier.wrapContentSize(), colors = ButtonDefaults.buttonColors(
            backgroundColor = Color("#5D4037".toColorInt()
            ),
            contentColor = Color.White
        )) {
            Text(text = textButton, maxLines = 1, textAlign = TextAlign.Center, style = TextStyle(color = Color.White))
        }

    }
}


@Composable
fun getUsername(context: Context, storeUserEmail: StoreUserEmail):String{
    var username by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        username = storeUserEmail.fetchEmail() // Gọi hàm fetchEmail()

    }
    return username
}