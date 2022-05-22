package com.example.hackathon_perevalfstr.domains

import com.google.gson.annotations.SerializedName

data class Level (

	@SerializedName("winter") val winter : String,
	@SerializedName("summer") val summer : String,
	@SerializedName("autumn") val autumn : String,
	@SerializedName("spring") val spring : String
)