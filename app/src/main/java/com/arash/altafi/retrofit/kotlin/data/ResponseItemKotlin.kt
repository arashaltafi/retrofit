package com.arash.altafi.retrofit.kotlin.data

import com.google.gson.annotations.SerializedName

data class ResponseItemKotlin(

	@field:SerializedName("namefamily")
	val namefamily: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
