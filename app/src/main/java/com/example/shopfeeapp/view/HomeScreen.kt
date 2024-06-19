package com.example.shopfeeapp.view

import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.shopfeeapp.R
import com.example.shopfeeapp.model.Drink
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navHostController: NavHostController,modifier: Modifier = Modifier,
               onClickDetailScreen:(Drink)->Unit){
    val image = listOf(
        R.drawable.imageslide3,
        R.drawable.imageslide2,
        R.drawable.imageslide1,
    )
    Column {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            trailingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .padding()
            )
        ImageSliderWithIndicator(images = image)
        RecipeScreen(navHostController = navHostController){
            Log.e("tung", "HomeScreen $it")
            onClickDetailScreen(it)

        }
    }

}

@Composable
fun ImageSliderItem(imageRes:Int){

    Image(painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)


    )
}

@Composable
fun ImageSliderWithIndicator(images: List<Int>){
    val currentIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true){
            delay(3000)
            currentIndex.value = (currentIndex.value +1) % images.size
        }

    }
    ConstraintLayout() {
        val (image,indicator) = createRefs()
        Box (
            modifier = Modifier
                .padding(top = 10.dp).constrainAs(image){
                    start.linkTo(parent.start)
                }
        ){
            ImageSliderItem(imageRes = images[currentIndex.value])
        }
        Row (modifier = Modifier.constrainAs(indicator){
            start.linkTo(image.start, margin = 2.dp)
            bottom.linkTo(image.bottom, margin = 5.dp)
            end.linkTo(image.end)

        }){
            images.forEachIndexed{
                    index, i ->
                Indicator(active = index == currentIndex.value)
                if (index < images.size -1){
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }

        }


    }
    Column(modifier = Modifier.fillMaxWidth()) {



    }
}
@SuppressLint("ResourceAsColor")
@Composable
fun Indicator(active:Boolean){
    val color = if(active) Color(R.color.brown) else Color.White
    val size = if (active) 10.dp else 10.dp
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(size)) {

    }

}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun preview(){
//    HomeScreen()
//}