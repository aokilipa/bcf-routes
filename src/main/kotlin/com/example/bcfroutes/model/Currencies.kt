package com.example.bcfroutes.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Currencies(
    @JsonProperty("AWG") var awg: Awg?,
    @JsonProperty("AFN") var afn: Afn?,
    @JsonProperty("AOA") var aoa: Aoa?,
)

data class Afn(
    @JsonProperty("name") var name: String?,
    @JsonProperty("symbol") var symbol: String?
)

data class Aoa(
    @JsonProperty("name") var name: String?,
    @JsonProperty("symbol") var symbol: String?
)

data class Awg(
    @JsonProperty("name") var name: String?,
    @JsonProperty("symbol") var symbol: String?
)