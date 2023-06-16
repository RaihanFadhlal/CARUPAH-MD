package com.carupahmobiledev.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetectResponse(

    @field:SerializedName("predicted_class")
    val predicted_class: String

)
