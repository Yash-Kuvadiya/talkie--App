package com.example.talkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.talkie.chatbot.ChatListScreen
import com.example.talkie.Screens.LoginScreen
import com.example.talkie.Screens.ProfileScreen
import com.example.talkie.Screens.SignUpScreen
import com.example.talkie.Screens.StatusScreen
import com.example.talkie.chatbot.ChtaBotVM
import com.example.talkie.ui.theme.LCViewModel
import com.example.talkie.ui.theme.TalkieTheme
import dagger.hilt.android.AndroidEntryPoint


sealed class Destinationscreen(var route : String){
    object SignUp : Destinationscreen("signup")
    object Login : Destinationscreen("login")
    object Profile : Destinationscreen("profile")
    object ChatList : Destinationscreen("chatList")
    object SingleChat : Destinationscreen("singleChat/{chatId}"){
        fun createRoute(id : String) = "singleChat/$id"
    }
    object StatusList : Destinationscreen("statusList")
    object SingleStatus : Destinationscreen("singleStatus/{userId}"){
        fun createRoute(userId : String) = "singleStatus/$userId"
    }
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TalkieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatAppNavigation()
                }
            }
        }
    }

    @Composable
    fun ChatAppNavigation() {

        val navController = rememberNavController()
       var vm = hiltViewModel<LCViewModel>()

        NavHost(navController = navController, startDestination = Destinationscreen.SignUp.route ){

            composable(Destinationscreen.SignUp.route){
                SignUpScreen(navController,vm)
            }
            composable(Destinationscreen.Login.route){
                LoginScreen(navController=navController,vm=vm)
            }
            composable(Destinationscreen.ChatList.route){
                ChatListScreen(navController=navController,vm = vm, viewModel = ChtaBotVM())
            }
            composable(Destinationscreen.StatusList.route){
                 StatusScreen(navController=navController,vm = vm)
            }
            composable(Destinationscreen.Profile.route){
                ProfileScreen(navController=navController,vm = vm)
            }
        }

    }
}

