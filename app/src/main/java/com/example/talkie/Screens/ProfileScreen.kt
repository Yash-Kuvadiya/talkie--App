package com.example.talkie.Screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.talkie.CommonDivider
import com.example.talkie.CommonImage
import com.example.talkie.CommonProgressBaar
import com.example.talkie.Destinationscreen
import com.example.talkie.R
import com.example.talkie.navigateTo
import com.example.talkie.ui.theme.LCViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController, vm: LCViewModel) {

    val inProgress = vm.inProcess.value
    if (inProgress) {
        CommonProgressBaar()
    } else {
        val userData = vm.userData.value
        var name by rememberSaveable {
            mutableStateOf(userData?.name?:"")
        }
        var number by rememberSaveable {
            mutableStateOf(userData?.number?:"")
        }
        Column {
            ProfileContent(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp),
                vm = vm,
                name = name,
                number = number,
                onNameChange = {name=it},
                onNumberChange = { number=it},
                onSave = {
                         vm.createOrUpadateProfile(
                             name=name,number = number
                         )
                },
                onBack = {
                         navigateTo(navController = navController , route = Destinationscreen.ChatList.route)
                },
                onLogOut = {
                    vm.logout()
                    navigateTo(navController = navController , route = Destinationscreen.Login.route)
                }
             )
         /*   BottumNavigationMenu(
                selectedItem = BottomNavigationItem.PROFILE,
                navController = navController
            )*/
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    modifier: Modifier,
    vm:LCViewModel,
    name : String,
    number : String,
    onNameChange:(String)-> Unit,
    onNumberChange:(String)->Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    onLogOut:() -> Unit
) {
        val imageUrl = vm.userData.value?.imageUrl
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // .padding(1.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // back button
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .padding(13.dp)
                    .clickable {
                        onBack.invoke()
                    }
            )
            // alt+ctrl+l -> rearrange the code
            // save button
            Text(text = "Save",
                color = Color.White,
                modifier = Modifier
                    .padding(13.dp)
                    .clickable {
                        // onSave.invoke()
                    }
            )
        }
            CommonDivider()
            // profile image show
           // ProfileImage(imageUrl = imageUrl , vm = vm)
            staticImage()
           // CommonDivider()
            Text(text = "Heyy , I'M Yash Kuvadiya ",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 105.dp)
                )
            Text(text = "(Android Developer)",
                modifier = Modifier
                    .padding(start = 128.dp, bottom = 10.dp)
                )
            Divider()
            Text(text = "Talkie is an innovative application developed using Kotlin and Jetpack Compose, designed to seamlessly integrate with Gemini AI. By leveraging the power of Jetpack Compose, Talkie provides a modern and intuitive user interface, offering a smooth user experience. The integration with Gemini AI allows Talkie to fetch and display information in a smart and efficient manner, enhancing the app's functionality and usability. With its focus on simplicity and effectiveness, Talkie delivers a perfect blend of technology and user-centric design, making it a standout application in the realm of AI-powered solutions.",
                modifier=Modifier
                    .padding(start = 25.dp, end = 30.dp, top = 10.dp)
                )

        /*
            // name edit
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement =  Arrangement.Center
            ){
                OutlinedTextField(
                    value = name,
                   // leadingIcon = { Icon(imageVector = Icons.Default.Edit , contentDescription ="" )},
                    onValueChange = {
                        onNameChange
                    },

                   // keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                   // label = { Text(text = "Name")},
                   // placeholder = { Text(text = "Enter Your Name")},

                    )

            }
            // number edit
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    value = number,
                    leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription ="" )},
                    onValueChange = {
                        onNumberChange
                    },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Number")},
                    placeholder = { Text(text = "Enter Your Name")},

                    )
            }*/
            // logOut
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Logout",
                        fontWeight = FontWeight.Bold,
                        modifier= Modifier
                            .clickable {
                                onLogOut.invoke()
                            }
                            .padding(10.dp)
                    )
                }


            }
        }
}


@Composable
fun ProfileImage(imageUrl : String? , vm : LCViewModel) {
        val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent()

        ){
            uri->
            uri?.let {
                vm.uploadProfileImage(uri)
            }
        }
    Box (
        modifier = Modifier
            .height(intrinsicSize = IntrinsicSize.Min)
    ){
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                // this will be take image from gallery
                .clickable {
                    launcher.launch("image/*")
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card (
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
                ){
                CommonImage(data = imageUrl)
            }
            Text(text = "Change Profile Image")
        }
        if (vm.inProcess.value){
            CommonProgressBaar()
        }
    }
}

@Composable
fun staticImage() {
    Box(
        modifier = Modifier
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            // this will be take image from gallery
            //.clickable {
            //       launcher.launch("image/*")
            //    },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.user123 ), contentDescription ="" )
            }
        }
    }
}