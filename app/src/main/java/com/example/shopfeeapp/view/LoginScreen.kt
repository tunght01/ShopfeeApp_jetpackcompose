package com.example.shopfeeapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shopfeeapp.R
import com.example.shopfeeapp.datastore.StoreUserEmail
import com.example.shopfeeapp.model.NavigationBottomScreen
import com.example.shopfeeapp.model.Screen
import com.example.shopfeeapp.model.User
import com.example.shopfeeapp.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@SuppressLint("ResourceAsColor")
@Composable
fun LoginScreen(

    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onClickToMainScreen:(Boolean)->Unit,
    onClickToSignupScreen:()->Unit,

) {

    val viewModel: LoginViewModel = viewModel()
    val viewstate by viewModel.usersState
    var emailValue by remember { mutableStateOf("") }
    var passwprdValue by remember { mutableStateOf("") }
    var check by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    // Create an instance of StoreUserEmail
    val storeUserEmail = StoreUserEmail(context)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp), // Adjust padding as needed
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Description of the image"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            OutlineTextFieldCustom(value = emailValue) {
                emailValue = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            OutlineTextFieldCustom(value = passwprdValue) {
                passwprdValue = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            for (user :User in viewstate.list) {
                if (emailValue == user.email) {
                    check = true
                    break
                } else check = false
            }
            Button(
                onClick = {
                    if(check){
                        scope.launch {
                            storeUserEmail.saveEmail(emailValue)
                        }
                    }
                    onClickToMainScreen(check)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(R.color.brown),
                    contentColor = Color(R.color.brown),
                ),
                enabled = (emailValue.isNotEmpty() && passwprdValue.isNotEmpty()),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Đăng nhập", color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Quên mật khẩu?",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .defaultMinSize()
                    .clickable { }
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp) // Adjust padding as needed,
            , verticalArrangement = Arrangement.Center

        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Bạn chưa có tài khoản? ", textAlign = TextAlign.Center,style = TextStyle(
                    fontSize = 12.sp
                ))
                Text(
                    text = "Đăng ký",style = TextStyle(
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.clickable {
                        onClickToSignupScreen()

                    },
                    color = MaterialTheme.colorScheme.primary // Optional: change color for clickable text
                )
            }
        }
    }
}


@Composable
fun OutlineTextFieldCustom(
    value:String,
    onValueSchanged:(String)->Unit
){
        OutlinedTextField(
            value = value,
            onValueChange = onValueSchanged,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .padding()
            )
}
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun LoginScreenPreview(){
//    LoginScreen()
//}
