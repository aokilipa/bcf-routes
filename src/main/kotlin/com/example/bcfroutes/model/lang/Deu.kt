package com.example.bcfroutes.model.lang

import com.fasterxml.jackson.annotation.JsonProperty

data class Deu (
	@JsonProperty("official") var official : String?,
	@JsonProperty("common") var common : String?
)