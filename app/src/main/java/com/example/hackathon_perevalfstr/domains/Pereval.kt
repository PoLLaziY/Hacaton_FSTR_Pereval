package com.example.hackathon_perevalfstr.domains

import com.google.gson.annotations.SerializedName

data class Pereval (

	@SerializedName("id") val id : Int,
	@SerializedName("beautyTitle") val beautyTitle : String,
	@SerializedName("title") val title : String,
	@SerializedName("other_titles") val other_titles : String,
	@SerializedName("connect") val connect : String,
	@SerializedName("add_time") val add_time : String,
	@SerializedName("user") val user : User,
	@SerializedName("coords") val coords : Coords,
	@SerializedName("type") val type : String,
	@SerializedName("level") val level : Level,
	@SerializedName("images") val images : List<Images>
)