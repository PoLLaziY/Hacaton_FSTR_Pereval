package com.example.hackathon_perevalfstr.domains

import com.google.gson.annotations.SerializedName

data class Images (

	@SerializedName("url") val url : String,
	@SerializedName("title") val title : String
)