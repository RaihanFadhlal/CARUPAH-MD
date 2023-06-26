package com.carupahmobiledev.data.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(

	@field:SerializedName("Response")
	val response: List<BankSampahDetail>
)

data class BankSampahDetail(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("numberPhone")
	val numberPhone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String,

)
