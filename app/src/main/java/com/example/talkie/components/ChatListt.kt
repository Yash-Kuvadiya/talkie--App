package com.example.talkie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.talkie.chatbot.ChatData
import com.example.talkie.chatbot.ChatRoleEnum

@Composable
fun ChatListt(
    list : MutableList<ChatData>
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(list){
            if (it.role == ChatRoleEnum.USER.role){
                Text(
                    text = it.message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(12.dp),
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
            else{
                Text(
                    text = it.message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(12.dp),
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
        }
    }
}