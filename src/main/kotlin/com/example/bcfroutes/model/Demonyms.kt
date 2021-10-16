package com.example.bcfroutes.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Demonyms(
    @JsonProperty("eng") var eng: Eng?,
    @JsonProperty("fra") var fra: Fra?
)