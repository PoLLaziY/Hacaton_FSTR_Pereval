package com.example.hackathon_perevalfstr.domains

import com.google.gson.annotations.SerializedName

data class Coords (

	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("height") val height : Int
)