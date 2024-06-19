package com.example.shopfeeapp.view

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.size.Size
import com.example.shopfeeapp.datastore.StoreUserEmail
import com.example.shopfeeapp.model.Screen
import com.example.shopfeeapp.viewmodel.LoginViewModel
import com.example.shopfeeapp.viewmodel.ProductViewModel
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun ProfileScreenScreen(
    onClickRespone:()->Unit,
    onClickContact:()->Unit,
    onClickChangePassword:()->Unit,
    onClickLogout: () -> Unit

){
    val loginViewModel: LoginViewModel = viewModel()
    val viewstate by loginViewModel.usersState

    val context = LocalContext.current
    val storeUserEmail = StoreUserEmail(context)
    var email by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        email = storeUserEmail.fetchEmail() // Gọi hàm fetchEmail()

    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(text = "Tài khoản")
        }
        Divider(
            color = Color.Gray, // Màu sắc của đường line
            thickness = 0.5.dp,  // Độ dày của đường line
            modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Email", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = email!!, style =  TextStyle(fontSize = 13.sp, color = Color(128,128,128)), modifier = Modifier.padding(start = 10.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.Gray, // Màu sắc của đường line
            thickness = 0.5.dp,  // Độ dày của đường line
            modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.padding(start = 10.dp)) {


            Spacer(modifier = Modifier.height(20.dp))
            ProfileIcon(icon = Icons.Default.MailOutline, title = "Phản hồi"){
                onClickRespone()
            }
            Spacer(modifier = Modifier.height(20.dp))
            ProfileIcon(icon = Icons.Default.Phone, title = "Liên hệ"){
                onClickContact()
            }
            Spacer(modifier = Modifier.height(20.dp))
            ProfileIcon(icon = Icons.Default.Edit, title = "Đổi mật khẩu"){
                onClickChangePassword()
            }
            Spacer(modifier = Modifier.height(20.dp))
            ProfileIcon(icon = Icons.Default.ExitToApp, title = "Đăng xuất"){
                onClickLogout()
            }
        }

    }
}

@Composable
fun ProfileIcon(icon: ImageVector,title:String, onClick:()->Unit){
    Row(modifier = Modifier.fillMaxWidth().clickable {
        onClick()
    }, verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(30.dp,30.dp), tint = Color(128,128,128))
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = title, style = TextStyle(fontSize = 16.sp, color = Color(128,128,128)))

    }



}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ProfileScreenScreenPreview(){
//    ProfileScreenScreen()
//}