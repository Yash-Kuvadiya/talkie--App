package com.example.talkie.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.talkie.CheckSignedIn
import com.example.talkie.CommonProgressBaar
import com.example.talkie.Destinationscreen
import com.example.talkie.R
import com.example.talkie.navigateTo
import com.example.talkie.ui.theme.LCViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(vm : LCViewModel , navController : NavController) {

    CheckSignedIn(vm = vm, navController = navController )

    Box (modifier = Modifier
        .fillMaxSize()){

        Column(modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            val focus = LocalFocusManager.current


            Image(
                painter = painterResource(id = R.drawable.talkieimage2),
                contentDescription = "",
                modifier = Modifier
                    .width(200.dp)
                    .padding(10.dp)
            )
            Text(
                text = "Sign In",
                modifier = Modifier
                    .padding(10.dp)
                ,
                fontSize = 35.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )

            //email textField
            OutlinedTextField(
                value = emailState.value,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription ="" ) },
                onValueChange = {
                    emailState.value=it
                },
                label = { Text(text = "E-mail")},
                placeholder = { Text(text = "Enter Your E-mail")}
            )
            // password TextField
            OutlinedTextField(
                value = passwordState.value,
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription ="" ) },
                onValueChange = {
                    passwordState.value=it
                },
                label = { Text(text = "Password")},
                placeholder = { Text(text = "Enter Your Password")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                onClick = {
                          vm.LoginIn(emailState.value.text , passwordState.value.text  )
                },
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 40.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Sign In",
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 83.dp, end = 83.dp),
                    fontSize = 18.sp
                )
            }
            Text(
                text = "New User? Go TO Sign Up >",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, Destinationscreen.SignUp.route)
                    }
            )


        }

    }
    if(vm.inProcess.value){
        CommonProgressBaar()
    }

}