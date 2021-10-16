package com.example.bcfroutes.model.lang

import com.fasterxml.jackson.annotation.JsonProperty

data class Est(
    @JsonProperty("official") var official: String?,
    @JsonProperty("common") var common: String?
)