package com.example.shopfeeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.shopfeeapp.R
import com.example.shopfeeapp.data.TextBold
import com.example.shopfeeapp.extension.DividerLine
import com.example.shopfeeapp.extension.DividerLine2
import com.example.shopfeeapp.extension.HeaderIconBack
import com.example.shopfeeapp.extension.textFileWhite
import com.example.shopfeeapp.extension.textFileWhiteBold
import com.example.shopfeeapp.model.FomartUtility

@Composable
fun MethodPayment(onClickBack:()->Unit, onClickChoseMethod:(String)->Unit){
    val isCheck1 = remember {
        mutableStateOf(false)
    }
    val isCheck2 = remember {
        mutableStateOf(false)
    }
    val isCheck3 = remember {
        mutableStateOf(false)
    }
    val isCheck4 = remember {
        mutableStateOf(false)
    }
    val choose = remember {
        mutableStateOf("")
    }
    Column {
        Column (modifier = Modifier.weight(1f)){
            HeaderIconBack(title = "Phương thức thanh toán", onClickBack = {

            })
            DividerLine2()

            MethodPaymentItem(image = R.drawable.img_4,
                title = "Thanh toán tiền mặt",
                isCheck = isCheck1.value,
                description = "Thanh toán khi nhận hàng",
                onChangeChecked = {
                    isCheck1.value = !isCheck1.value
                    choose.value = "Thanh toán khi nhận hàng"
                    isCheck2.value = false
                    isCheck3.value = false
                    isCheck4.value = false

                })
            MethodPaymentItem(image = R.drawable.creditcard,
                title = "Credit or detail card",
                isCheck = isCheck2.value,
                description = "Thẻ visa hoặc Mastercard",
                onChangeChecked = {
                    isCheck2.value = !isCheck2.value
                    choose.value = "Thẻ visa hoặc Mastercard"
                    isCheck1.value = false
                    isCheck3.value = false
                    isCheck4.value = false

                })
            MethodPaymentItem(image = R.drawable.img_3,
                title = "Chuyển khoản ngân hàng",
                isCheck = isCheck3.value,
                description = "Tự động xác nhận",
                onChangeChecked = {
                    isCheck3.value = !isCheck3.value
                    choose.value = "Chuyển khoản ngân hàng"
                    isCheck1.value = false
                    isCheck2.value = false
                    isCheck4.value = false
                })
            MethodPaymentItem(image = R.drawable.zalopay,
                title = "ZaloPay",
                isCheck = isCheck4.value,
                description = "Tự động xác nhận",
                onChangeChecked = {
                    isCheck4.value = !isCheck4.value
                    choose.value = "ZaloPay"
                    isCheck2.value = false
                    isCheck1.value = false
                    isCheck3.value = false
                })
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(
                color = Color("#5D4037".toColorInt()),
                shape = RoundedCornerShape(5.dp)
            )
            .size(40.dp)
            .clickable {
                onClickChoseMethod(choose.value)
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Text(text = "ĐỒNG Ý", color = Color.White)

        }

    }


}

@Composable
fun MethodPaymentItem(image:Int, title:String, description:String, isCheck:Boolean, onChangeChecked:()->Unit){
      Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
          Row (modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)){
              Image(painter = painterResource(id = image), contentDescription = null, modifier = Modifier
                  .size(50.dp)
                  .clip(shape = RoundedCornerShape(14.dp)))
              Column (modifier = Modifier.padding(start = 5.dp)){
                  TextBold(title = title)
                  Text(text = "($description)")
              }
          }
          RadioButton(selected = isCheck, onClick = onChangeChecked, colors = RadioButtonDefaults.colors(
              selectedColor = Color("#5D4037".toColorInt()),
//              disabledColor = Color("#F4EFEB".toColorInt())
          ))
      }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MethodPaymentPreview(){
//    MethodPayment(){
//    }
//}