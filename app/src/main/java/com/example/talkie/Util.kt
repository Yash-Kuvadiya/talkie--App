package com.example.talkie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.PopUpToBuilder
import coil.compose.rememberImagePainter
import com.example.talkie.ui.theme.LCViewModel


// all file / function common data and function will be write in this file

fun navigateTo(navController: NavController,route : String){

    navController.navigate(route){

        launchSingleTop=true

    }
}

// composable function for loading event for all pages 
@Composable
fun CommonProgressBaar() {
    Row (
        modifier = Modifier
            .alpha(0.5f)
            .background(Color.LightGray)
            .clickable(enabled = false) { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator()
    }
}


@Composable
fun CommonDivider() {
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier
            .alpha(0.3f)
            .padding(top = 8.dp, bottom = 8.dp)
    )
}

// code for common image display in entire application
@Composable
fun CommonImage(
    data : String?,
    modifier: Modifier = Modifier.wrapContentSize(),
    contentScale: ContentScale = ContentScale.Crop
) {
     val painter = rememberImagePainter(data=data)
    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier,
        contentScale=contentScale
        )
}

@Composable
fun CheckSignedIn(vm: LCViewModel, navController: NavController) {

        val alreadySignedIn = remember {
            mutableStateOf(false)
        }
        val signIn = vm.signIn.value
        if(signIn && !alreadySignedIn.value){
            alreadySignedIn.value=true
            navController.navigate(Destinationscreen.ChatList.route)
            {
                popUpTo(0)
            }
        }
}

@Composable
fun TitleText(txt : String) {
    Text(text = txt,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        modifier = Modifier.padding(8.dp)
        )
}