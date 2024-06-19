package com.example.shopfeeapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.shopfeeapp.R
import com.example.shopfeeapp.model.Drink
import com.example.shopfeeapp.model.FomartUtility

@Composable
fun DetailScreen(drink: Drink, onClickCart:()->Unit) {

    val (isColdSelected, setColdSelected) = remember { mutableStateOf(false) }
    val (isHotSelected, setHotSelected) = remember { mutableStateOf(false) }
    val isSizeSmallSelected = remember { mutableStateOf(false) }
    val isSizeMediumSelected = remember { mutableStateOf(false) }
    val isSizeLargeSelected = remember { mutableStateOf(false) }
    val (isSugarNormalSelected , setSugarNormalSelected ) = remember { mutableStateOf(false) }
    val (isSugarReducedSelected , setSugarReducedSelected ) = remember { mutableStateOf(false) }
    val (isIceNormalSelected , setIceNormalSelected ) = remember { mutableStateOf(false) }
    val (isIceReducedSelected , setIceReducedSelected) = remember { mutableStateOf(false) }
    var isChantrau  = remember { mutableStateOf(false) }
    var isTranChauTrang  = remember { mutableStateOf(false) }
    var isDuaKho  = remember { mutableStateOf(false) }
    var isThach  = remember { mutableStateOf(false) }
//    val price = remember { mutableStateOf(30000) }
    val price = remember { mutableStateOf(drink.Price) }
    val quality = remember { mutableStateOf(1) }
    val totalPrice = remember(price.value, quality.value) {
        mutableStateOf(price.value * quality.value )
    }
    val textNote = remember {
        mutableStateOf("")
    }
    Column {
        ConstraintLayout (modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .weight(1f)){
            val (image, boxDetail, boxOption, boxChose, note) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.coffee2),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                    }
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,

                )

            Box(
                modifier = Modifier
                    .constrainAs(boxDetail) {
                        top.linkTo(image.bottom, margin = (-40).dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = 16.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )  // Apply background first
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )  // Apply border second
                    .padding(10.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = drink.name,
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(text = FomartUtility().format(price = price.value), style = TextStyle(fontWeight = FontWeight.Bold))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = drink.Description, modifier = Modifier.fillMaxWidth(0.7f))
//                        Text(text = "hihihihihihih", modifier = Modifier.fillMaxWidth(0.7f))
                        Spacer(modifier = Modifier.width(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                if (quality.value > 1) {
                                    quality.value--

                                }
                            }) {
                                Icon(Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(15.dp))
                            }
                            Text(
                                text = "${quality.value}",
//                            modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            IconButton(onClick = {
                                quality.value++

                            }) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(15.dp))
                            }
                        }
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = "Star",
                                modifier = Modifier.size(15.dp)
                            )
                            Text(text = FomartUtility().calculateAverageRating(drink.rates).toString(), modifier = Modifier.padding(start = 4.dp))
                            Text(text = "(${drink.rates?.size.toString()})", modifier = Modifier.padding(start = 4.dp))
                            Text(text = "- Xếp hạng & đánh giá", modifier = Modifier.padding(start = 4.dp))
                        }
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.wrapContentSize()) {
                            Icon(
                                Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .constrainAs(boxOption) {
                        top.linkTo(boxDetail.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = 16.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )  // Apply background first
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )  // Apply border second
                    .padding(10.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tùy chỉnh",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Đồ uống")
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckboxItem(
                                text = "Đá",
                                isChecked = isColdSelected,
                                onCheckedChange = {
                                    setColdSelected(!isColdSelected)
                                    setHotSelected(false)
                                }
                            )
                            CheckboxItem(
                                text = "Nóng",
                                isChecked = isHotSelected,
                                onCheckedChange = { setHotSelected(!isHotSelected)
                                    setColdSelected(false)}
                            )


                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
//                        .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Kích thước")
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckboxItem(
                                text = "Nhỏ",
                                isChecked = isSizeSmallSelected.value,
                                onCheckedChange = {
                                    isSizeSmallSelected.value = !isSizeSmallSelected.value

                                    if (isSizeSmallSelected.value) {
                                        if (isSizeMediumSelected.value) {
                                            totalPrice.value -= 10000*quality.value
                                        }
                                        if (isSizeLargeSelected.value) {
                                            totalPrice.value -= 15000*quality.value
                                        }
                                        isSizeMediumSelected.value = false
                                        isSizeLargeSelected.value = false

                                    }
                                }
                            )

                            CheckboxItem(
                                text = "Vừa",
                                isChecked = isSizeMediumSelected.value,
                                onCheckedChange = {
                                    isSizeMediumSelected.value = !isSizeMediumSelected.value

                                    if (isSizeMediumSelected.value) {

                                        if (isSizeLargeSelected.value) {
                                            totalPrice.value -= 15000*quality.value
                                        }
                                        isSizeSmallSelected.value = false
                                        isSizeLargeSelected.value = false
                                        totalPrice.value += 10000*quality.value
                                    } else {
                                        totalPrice.value -= 10000*quality.value
                                    }
                                }
                            )

                            CheckboxItem(
                                text = "Lớn",
                                isChecked = isSizeLargeSelected.value,
                                onCheckedChange = {
                                    isSizeLargeSelected.value = !isSizeLargeSelected.value

                                    if (isSizeLargeSelected.value) {

                                        if (isSizeMediumSelected.value) {
                                            totalPrice.value -= 10000*quality.value
                                        }
                                        isSizeSmallSelected.value = false
                                        isSizeMediumSelected.value = false
                                        totalPrice.value += 15000*quality.value
                                    } else {
                                        totalPrice.value -= 15000*quality.value
                                    }
                                }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
//                        .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Đường")
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckboxItem(
                                text = "Bình thường",
                                isChecked = isSugarNormalSelected,
                                onCheckedChange = {
                                    setSugarNormalSelected(!isSugarNormalSelected)
                                    setSugarReducedSelected(false)

                                }
                            )
                            CheckboxItem(
                                text = "Giảm bớt",
                                isChecked = isSugarReducedSelected,
                                onCheckedChange = {
                                    setSugarNormalSelected(false)
                                    setSugarReducedSelected(!isSugarReducedSelected)
                                }
                            )

                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
//                        .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Đá")
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CheckboxItem(
                                text = "Bình thường",
                                isChecked = isIceNormalSelected,
                                onCheckedChange = {
                                    setIceNormalSelected(!isIceNormalSelected)
                                    setIceReducedSelected(false)
                                }
                            )
                            CheckboxItem(
                                text = "Giảm bớt",
                                isChecked = isIceReducedSelected,
                                onCheckedChange = {
                                    setIceNormalSelected(false)
                                    setIceReducedSelected(!isIceReducedSelected)
                                }
                            )


                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .constrainAs(boxChose) {
                        top.linkTo(boxOption.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = 16.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )  // Apply background first
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )  // Apply border second
                    .padding(10.dp)
            ) {
                Column {
                    CheckboxOptionItem(text = "Trân châu đen", price = 5000, isChecked = isChantrau.value){
                        isChantrau.value = !isChantrau.value
                        if(isChantrau.value){
                            totalPrice.value += 5000
                        }else{
                            totalPrice.value -= 5000
                        }
                    }
                    CheckboxOptionItem(text = "Trân châu trắng", price = 6000, isChecked = isTranChauTrang.value){
                        isTranChauTrang.value = !isTranChauTrang.value
                        if(isTranChauTrang.value){
                            totalPrice.value += 6000
                        }else{
                            totalPrice.value -= 6000
                        }
                    }
                    CheckboxOptionItem(text = "Dừa khô", price = 3000, isChecked = isDuaKho.value){
                        isDuaKho.value = !isDuaKho.value
                        if(isDuaKho.value){
                            totalPrice.value += 3000
                        }else{
                            totalPrice.value -= 3000
                        }
                    }
                    CheckboxOptionItem(text = "Thạch 7 màu", price = 7000, isChecked = isThach.value){
                        isThach.value = !isThach.value
                        if(isThach.value){
                            totalPrice.value += 7000
                        }else{
                            totalPrice.value -= 7000
                        }
                    }

                }
            }
            Column ( modifier = Modifier
                .constrainAs(note) {
                    top.linkTo(boxChose.bottom)
                }
                .padding(horizontal = 16.dp, vertical = 10.dp)){
                Text(text = "Ghi chú",
                    style = TextStyle(fontWeight = FontWeight.Bold))
                TextField(value = textNote.value, onValueChange = { textNote.value = it}, maxLines = 6, modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(width = 0.5.dp, color = Color.Gray)
                    ), colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White

                ), placeholder = {Text(text = "Không bắt buộc")},)
            }

        }
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
                    .fillMaxWidth(0.45f)
                    .padding(10.dp)){
                    Text(text = "Tổng tiền")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = FomartUtility().format(price = totalPrice.value), style = TextStyle(fontWeight = FontWeight.Bold))
                }
                Button(onClick = { onClickCart() }, modifier = Modifier.wrapContentSize(), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color("#5D4037".toColorInt()
                    ),
                    contentColor = Color.White
                )) {
                    Text(text = "Thêm vào giỏ hàng", maxLines = 1, textAlign = TextAlign.Center)
            }

        }

    }



}
@SuppressLint("ResourceAsColor")
@Composable
fun CheckboxItem(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = if (isChecked) Color("#5D4037".toColorInt()) else Color.White,
                    shape = RoundedCornerShape(5.dp)
                )
                .border(
                    BorderStroke(width = 1.2.dp, color = Color("#5D4037".toColorInt())),
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable { onCheckedChange(!isChecked) },
            contentAlignment = Alignment.Center
        ) {
            Text(text = text,
                modifier = Modifier.padding(5.dp),
                color = if (!isChecked) Color.Black else Color.White)

        }

        Spacer(modifier = Modifier.width(8.dp))


    }
}
@Composable
fun CheckboxOptionItem(
    text: String,
    isChecked: Boolean,
    price:Int,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
//                        .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "+" + FomartUtility().format(price = price) )
            Checkbox(checked = isChecked, onCheckedChange = {
                onCheckedChange()
            },
                colors = CheckboxDefaults.colors(
                    Color("#5D4037".toColorInt())
                ))

        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DetailScreenPreview() {
//    DetailScreen()
//}