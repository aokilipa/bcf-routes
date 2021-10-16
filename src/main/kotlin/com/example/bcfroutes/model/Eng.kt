package com.example.bcfroutes.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Eng (
	@JsonProperty("f") var f : String?,
	@JsonProperty("m") var m : String?
)