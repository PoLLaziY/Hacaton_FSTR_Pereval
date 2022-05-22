package com.example.hackathon_perevalfstr.domains

import com.google.gson.annotations.SerializedName

data class User (

	@SerializedName("id") val id : Int,
	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : String,
	@SerializedName("fam") val fam : String,
	@SerializedName("name") val name : String
)