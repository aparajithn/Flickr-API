package com.example.apprenticeassignment2.data


import com.google.gson.annotations.SerializedName

//Data class to parse each photo
// needed id, secretId and serverId for photo url construction
// title to parse the heading which we're showing for each card in recycleview
data class Photo(
    @SerializedName("id") val id: String,
    @SerializedName("secret") val secretId: String,
    @SerializedName("server") val serverID: String,
    @SerializedName("title") val title: String
)