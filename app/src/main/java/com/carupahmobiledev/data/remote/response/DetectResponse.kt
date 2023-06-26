package com.carupahmobiledev.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetectResponse(
    @field:SerializedName("predicted_class")
    val predicted_class: String
)

data class Response(

    @field:SerializedName("detail")
    val detail: List<DetailItem>
)

data class DetailItem(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("loc")
    val loc: List<String>,

    @field:SerializedName("type")
    val type: String
)
