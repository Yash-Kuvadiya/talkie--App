package com.example.talkie.chatbot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.talkie.CommonProgressBaar
import com.example.talkie.TitleText
import com.example.talkie.components.ChatFooter
import com.example.talkie.components.ChatHeader
import com.example.talkie.components.ChatListt
import com.example.talkie.ui.theme.LCViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController,vm : LCViewModel,viewModel : ChtaBotVM ) {
    val inProgress = vm.inProcessChat
  /*  Text(
        text = "Chats ",
        modifier = Modifier
            .padding(10.dp)
        ,
        fontSize = 35.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
    if (inProgress.value){
        CommonProgressBaar()
    }
    else{
        val chats = vm.chats.value
        val userData = vm.userData.value
        val showDialog = remember {
            mutableStateOf(false)
        }
        val onFabClick:()-> Unit ={showDialog.value=true}
        val onDismiss:()-> Unit = {showDialog.value=false}
        val onAddChat:(String)->Unit= {
            vm.onAddChat(it)
            showDialog.value= false
        }

        // it will display content one upon another
        Scaffold(
            floatingActionButton = {
                },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                ) {
                    TitleText(txt = "Chats")
                    FAB(
                        showDialog = showDialog.value,
                        onFabClick = onFabClick,
                        onDismiss = onDismiss,
                        onAddChat = onAddChat
                    )
                //   BottumNavigationMenu(selectedItem = BottomNavigationItem.CHATLIST, navController = navController )
                }
            }
        )
    }*/
    ChatHeader(navController)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Box (modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
            ){
            if (viewModel.list.isEmpty()){
                Text(text = "No Chats Available")
            }
            else {
                ChatListt(list = viewModel.list)
            }
        }

        ChatFooter {
            if (it.isNotEmpty()){
                viewModel.sendMessage(it)
            }
        }
    }

}









/*
// After Add button
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAB(
    showDialog : Boolean,
    onFabClick:()->Unit,
    onDismiss:()->Unit,

onAddChat:(String)->Unit
) {
    val addChatNumber = remember {
        mutableStateOf("")
    }
    if(showDialog){
        AlertDialog(onDismissRequest = {
            onDismiss.invoke()
            addChatNumber.value = ""
        },
            confirmButton = {
                Button(onClick =
                {
                    onAddChat(addChatNumber.value)
                }) {
                    Text(text = "Add Chat")
                }
            },
            title = { Text(text = "Add Chat")},
            text = {
                OutlinedTextField(value = addChatNumber.value, onValueChange ={addChatNumber.value = it} )
            }
        )
        FloatingActionButton(
            onClick = { onFabClick },
            //color = Color.Black,
            shape = CircleShape,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Text(text = "Add")
            Icon(imageVector = Icons.Rounded.Add , contentDescription = "",tint = Color.Black)
        }
    }
}*/