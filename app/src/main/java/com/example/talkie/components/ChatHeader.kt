package com.example.talkie.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.talkie.Destinationscreen
import com.example.talkie.navigateTo

@Composable
fun ChatHeader(navController: NavController) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Talkie",
            modifier = Modifier
                .padding(10.dp)
            ,
            fontSize = 35.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )

        Icon(imageVector = Icons.Default.AccountBox,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .padding(top = 10.dp,end=10.dp)
                .clickable {
                    navigateTo(navController = navController, route = Destinationscreen.Profile.route)
                }
            )

    }


}