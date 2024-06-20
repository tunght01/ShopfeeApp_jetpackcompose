package com.example.shopfeeapp.view

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import com.example.shopfeeapp.R
import com.example.shopfeeapp.model.Screen
import com.example.shopfeeapp.viewmodel.LoginViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

@SuppressLint("ResourceAsColor")
@Composable
fun SignUpScreen(viewModel: LoginViewModel,
                 navHostController: NavHostController,
                 onClickToLoginScreen:()->Unit,
                 onCickSignUp:()->Unit,
                 modifier: Modifier = Modifier) {

    val viewstate by viewModel.usersState
    var userName by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    val  context = LocalContext.current
    fun register(){
        if (password.equals(confirmpassword)){
            val json = JSONObject().apply {
                put("username", userName)
                put("email", emailValue)
                put("password", password)
            }
            val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())
            viewModel.registerUser(requestBody) {
                onClickToLoginScreen()

            }
            Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
        }

    }

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
            Text(
                text = "Username",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            OutlineTextFieldCustom(value = userName) {
                userName = it
            }
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
            OutlineTextFieldCustom(value = password) {
                password = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Confirm password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            OutlineTextFieldCustom(value = confirmpassword) {
                confirmpassword = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    register()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(R.color.brown),
                    contentColor = Color(R.color.brown),
                ),
                enabled = (emailValue.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty() && confirmpassword.isNotEmpty()),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Đăng ký", color = Color.White,style = TextStyle(
                    fontSize = 12.sp
                ))
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Quên mật khẩu?",style = TextStyle(
                    fontSize = 12.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.defaultMinSize().clickable {  }
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
                Text(text = "Bạn chưa đã có tài khoản? ", textAlign = TextAlign.Center,style = TextStyle(
                    fontSize = 12.sp
                ))
                Text(
                    text = "Đăng nhập",style = TextStyle(
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.clickable {
                        navHostController.navigate(Screen.LoginScreen.route)
                    },
                    color = MaterialTheme.colorScheme.primary // Optional: change color for clickable text
                )
            }
        }
    }
}
