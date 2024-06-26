package com.example.talkie.data

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData (

    var userId : String?="",
    var name : String?="",
    var number : String?="",
    var imageUrl : String?="",
)
{
    @Exclude
    fun toMap()= mapOf(
        "userId" to userId,
        "name" to name ,
        "number" to number,
        "imageUrl" to imageUrl
    )

}

data class ChatData(
    val chatId : String?="",
    val user1 : ChatUser = ChatUser(),
    val user2 : ChatUser = ChatUser()
)
data class ChatUser(
    val userId: String?="",
    val name: String?="",
  //  val imageUrl: String?="",
    val number: String?=""
)