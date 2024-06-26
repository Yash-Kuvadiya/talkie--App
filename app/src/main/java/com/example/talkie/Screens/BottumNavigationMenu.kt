package com.example.talkie.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.talkie.Destinationscreen
import com.example.talkie.R
import com.example.talkie.navigateTo


enum class BottomNavigationItem(val icon : Int , val navDestination : Destinationscreen){
    CHATLIST(R.drawable.chat2,Destinationscreen.ChatList),
    STATUSLIST(R.drawable.status2,Destinationscreen.StatusList),
    PROFILE(R.drawable.user2,Destinationscreen.Profile)
}

@Composable
fun BottumNavigationMenu(
    selectedItem : BottomNavigationItem,
    navController: NavController
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 4.dp)
        .background(Color.White)
    ){
        for(item in BottomNavigationItem.values()){   // Above Enum class all value fetch here...and it will be stored in item
            Image(painter = painterResource(id = item.icon),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController,item.navDestination.route)
                    },
                colorFilter = if (item == selectedItem){
                    ColorFilter.tint(color = Color.Black)
                }
                else{
                    ColorFilter.tint(color = Color.Gray)
                }
                )
        }
    }

}