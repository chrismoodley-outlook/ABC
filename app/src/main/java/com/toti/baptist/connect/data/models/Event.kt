package com.toti.baptist.connect.data.models

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("location")
    val location: String
)
