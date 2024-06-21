package com.example.shopfeeapp.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.shopfeeapp.Api.api
import com.example.shopfeeapp.R
import com.example.shopfeeapp.data.TextBold
import com.example.shopfeeapp.model.DetailOrderCart
import com.example.shopfeeapp.model.Drink
import com.example.shopfeeapp.model.FomartUtility
import com.example.shopfeeapp.viewmodel.DetailCartViewModel
import com.example.shopfeeapp.viewmodel.ProductViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailOrderCartScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit
){
    Log.e("tung", "vao duoc composoble")
    val detailCartViewModel: DetailCartViewModel = viewModel()
    val viewstate by detailCartViewModel.DetailOrderState

    Log.e("tung", "khong vao duoc composoble")

    when{
        viewstate.error != null ->{
            Log.e("detail", "${viewstate.error}")
            Text("${viewstate.error}")
        }
        else ->{
            Log.e("detail", "${viewstate.list.size}")
            Log.e("detail", "thanhconmg roi")
            CartScreen(orderCart = viewstate.list, onClickBack = {
                onClickBack()
            })


        }
    }


}



@Composable
fun CartScreen(orderCart: List<DetailOrderCart>,onClickBack:()->Unit){
    val checkBoxStates = remember { mutableStateListOf<Boolean>().apply {
        addAll(List(orderCart.size) { false })
    } }


    Column (){
        Column (modifier = Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState())){
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = { onClickBack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(text = "Giỏ hàng")
            }
            Divider(
                color = Color.Gray, // Màu sắc của đường line
                thickness = 0.8.dp,  // Độ dày của đường line
                modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                orderCart.forEachIndexed { index, orderCartItem ->
                    if (index > 0) {
                        Divider(
                            color = Color.Gray,
                            thickness = 0.8.dp,

                        )
                    }
                    DetailCartItem(
                        detailOrderCart = orderCartItem,
                        isChecked = checkBoxStates[index],
                        onChangeCheckBox = { isChecked ->
                            checkBoxStates[index] = isChecked
                        })

                }
            }
//            LazyColumn {
//                items(orderCart){
//                        orderCartItem->
//                    DetailCartItem(detailOrderCart = orderCartItem)
//
//                }
//            }

            Divider(
                color = Color.LightGray, // Màu sắc của đường line
                thickness = 3.dp,  // Độ dày của đường line
                modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
            )
            MethodPayment("Phương thức thanh toán","Chưa chọn phương thức thanh toán"){

            }
            Divider(
                color = Color.LightGray, // Màu sắc của đường line
                thickness = 1.dp,  // Độ dày của đường line
                modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
            )
            MethodPayment("Địa chỉ giao hàng","Chưa chọn địa chỉ giao hàng"){

            }
            Divider(
                color = Color.LightGray, // Màu sắc của đường line
                thickness = 1.dp,  // Độ dày của đường line
                modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
            )
            MethodPayment("Khuyến mại","Chưa áp dụng mã khuyến mại"){

            }
            Divider(
                color = Color.LightGray, // Màu sắc của đường line
                thickness = 4.dp,  // Độ dày của đường line
                modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                TextBold(title = "Thanh toán")
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Text(text = "Giá")
                    Text(text = "60.000 vnd")
                }
                Text(text = "(1 đồ uống)")
            }
            Divider(
                color = Color.LightGray, // Màu sắc của đường line
                thickness = 1.dp,  // Độ dày của đường line
                modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {

                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Text(text = "Khuyến mại")
                    Text(text = "- 0.000 vnd")
                }
                Text(text = "Chưa áp dụng mã khuyến mại")
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
                .padding(10.dp)){
                Text(text = "Tổng tiền")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = FomartUtility().format(price = 60000),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.wrapContentSize(), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color("#5D4037".toColorInt()
                ),
                contentColor = Color.White
            )) {
                Text(text = "Đặt đơn hàng", maxLines = 1, textAlign = TextAlign.Center, style = TextStyle(color = Color.White))
            }

        }
    }


}
fun TotalCost(){

}
@Composable
fun MethodPayment(
    title:String,
    status:String,
    onClickOption:()->Unit,

    ){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 10.dp), horizontalArrangement = Arrangement.SpaceBetween){
        Column (){

            TextBold(title = title)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = status)

        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, Modifier.size(18.dp))
        }
    }


}
@Composable
fun DetailCartItem(detailOrderCart: DetailOrderCart,isChecked:Boolean, onChangeCheckBox:(Boolean)->Unit){
    Row (modifier = Modifier.padding(top= 10.dp, end = 10.dp)){
        Checkbox(checked = isChecked, onCheckedChange =
            onChangeCheckBox
        ,
            colors = CheckboxDefaults.colors(
                Color("#5D4037".toColorInt())
            ))
        Image(
            painter = rememberAsyncImagePainter("http://$api:1337"+detailOrderCart.drink.ImageUrl?.url),
            contentDescription = null,

            modifier = Modifier
                .size(40.dp) // Adjust size as needed
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Row (modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween){
                TextBold(title = detailOrderCart.drink.name)
                TextBold(title = FomartUtility().format(detailOrderCart.Price))

            }
            Row (modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween){
                Text(text = detailOrderCart.drink.Description, modifier = Modifier.weight(0.9f), maxLines = 2, style = TextStyle(fontSize = 12.sp))
                Text(text = "x" +detailOrderCart.Quantity.toString(),style = TextStyle(fontSize = 12.sp))
            }
            Row(
//                    verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween

            ) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.padding(10.dp))
                Row (verticalAlignment = Alignment.CenterVertically,){
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.DeleteOutline, contentDescription = null)
                    }
                    IconButton(onClick = {
//                        if (quality.value > 1) {
//                            quality.value--
//
//                        }
                    }) {
                        Icon(Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(15.dp))
                    }
                    Text(
                        text = "${detailOrderCart.Quantity}",
//                            modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    IconButton(onClick = {
//                        quality.value++

                    }) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(15.dp))
                    }

                }

            }
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CartScreenPreview(){
//    CartScreen()
//
//}