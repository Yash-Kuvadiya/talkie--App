package com.example.talkie.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.talkie.ui.theme.LCViewModel

@Composable
fun StatusScreen(navController: NavController, vm : LCViewModel) {
    Text(
        text = "Status",
        modifier = Modifier
            .padding(10.dp)
        ,
        fontSize = 35.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )


    BottumNavigationMenu(selectedItem = BottomNavigationItem.STATUSLIST, navController = navController )
}