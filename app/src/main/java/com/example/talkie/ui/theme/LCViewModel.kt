package com.example.talkie.ui.theme

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.talkie.data.ChatData
import com.example.talkie.data.Event
import com.example.talkie.data.USER_NODE
import com.example.talkie.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class LCViewModel @Inject constructor(
    val auth : FirebaseAuth,
    var db : FirebaseFirestore,
    val storage : FirebaseStorage
) : ViewModel(){



        // this variale for show process
        var inProcess = mutableStateOf(false)
        var inProcessChat = mutableStateOf(false)
        // this variable is for any event in the application
        var eventMutableState = mutableStateOf<Event<String>?>(null)
        var signIn = mutableStateOf(false)

        val userData = mutableStateOf<UserData?>(null)
        val chats  = mutableStateOf<List<ChatData>>(listOf())

        // if user is already signed in then it will be directly moven on chat list screen...below inint block is for that
        init {

                val currentUser = auth.currentUser
                signIn.value = currentUser!=null
                currentUser?.uid?.let {
                    getUserData(it)
                }
        }



        fun signup(name : String , number: String , email : String , password : String){
            inProcess.value=true


            // code for if null/empty field found in signUp
            if(name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()){
                handleException(customMessage = "please fill all the fields")
                return
            }

            inProcess.value = true
            db.collection(USER_NODE).whereEqualTo("number",number).get().addOnSuccessListener {
                    if(it.isEmpty){
                        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                            if(it.isSuccessful){
                                signIn.value = true
                                createOrUpadateProfile(name, number)
                            }
                            else{
                                handleException(it.exception, customMessage = "Sign In failed")
                            }
                        }
                    }
                    else{
                        handleException(customMessage = "Number Already Registered")
                        inProcess.value= false
                    }
            }




        }

        // this function is used for login functionality
        fun LoginIn(email: String,password: String){

            if (email.isEmpty() or password.isEmpty()){
                handleException(customMessage = "Please Filled All Information!!")
            }
            else{
                inProcess.value = true
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{

                        if (it.isSuccessful){
                            signIn.value = true
                            inProcess.value = false
                            auth.currentUser?.uid?.let {
                                getUserData(it)
                            }
                        }
                        else{
                            handleException(exception = it.exception , customMessage = "Login Failed")
                        }

                    }
            }

        }
        // function for upload image
        fun uploadProfileImage(uri: Uri){
            uploadImage(uri){
                createOrUpadateProfile(imageUrl = it.toString())
            }
        }
        fun uploadImage(uri: Uri,onSuccess:(Uri)->Unit){
            inProcess.value=true
            val storageRef = storage.reference
            val uuid = UUID.randomUUID()
            val  imageRef = storageRef.child("image/$uuid")
            val uploadTask = imageRef.putFile(uri)
            uploadTask.addOnSuccessListener {

                val result = it.metadata?.reference?.downloadUrl

                result?.addOnSuccessListener(onSuccess)
                inProcess.value=false

            }
                .addOnFailureListener {
                    handleException(it)
                }
        }

        // function for use profile
        fun createOrUpadateProfile(name : String?=null , number : String?=null , imageUrl : String?=null){
                val uid = auth.currentUser?.uid
                val userData = com.example.talkie.data.UserData(
                    userId = uid,
                    name = name?:userData.value?.name,
                    number = number?:userData.value?.number,
                    imageUrl = imageUrl?:userData.value?.imageUrl
                )
            // let block will executes whrn any value can be null
            uid?.let {
                inProcess.value= true
                db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                    if (it.exists()){
                        // upadate user data
                    }
                    else{

                        db.collection(USER_NODE).document(uid).set(userData)
                        inProcess.value=false
                        getUserData(uid)
                    }
                }
                    .addOnFailureListener{
                        handleException(it,"Can't Retrived User")
                    }
            }
        }

    private fun getUserData(uid : String) {
            inProcess.value= true
            db.collection(USER_NODE).document().addSnapshotListener{
                value , error->
                if(error!=null)
                {
                    Log.d("test","error")
                    handleException(error,"Can't Retrived User")
                }
                if (value != null){
                    Log.d("test","helloooo")

                    var user = value.toObject<UserData>()    // if Error is occures then doing userData to UserData
                  //  userData.value = UserData(userId = "1000010",name = "kajsjhs",number = "54454",imageUrl = "")
                    userData.value =  user
                    inProcess.value = false

                }
            }
//        Log.d("test",userData.value?.name.toString())
    }


    // this is exception function
        fun handleException(exception: Exception?=null , customMessage : String =""){
        Log.e("test","Live Chat Exception ",exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage?:""
        val message = if(customMessage.isNullOrEmpty()) errorMsg else customMessage

         eventMutableState.value = Event(message)
            inProcess.value=false
    }

    fun logout() {
        auth.signOut()
        signIn.value = false
        userData.value = null
        eventMutableState.value = Event("Logged Out")
    }

    fun onAddChat(it: String) {

    }

}
