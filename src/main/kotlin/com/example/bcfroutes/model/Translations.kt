package com.example.bcfroutes.model

import com.example.bcfroutes.model.lang.*
import com.fasterxml.jackson.annotation.JsonProperty


data class Translations(
    @JsonProperty("ces")
    var ces: Ces?,

    @JsonProperty("deu")
    var deu: Deu?,

    @JsonProperty("est")
    var est: Est?,

    @JsonProperty("fin")
    var fin: Fin?,

    @JsonProperty("fra")
    var fra: Fra?,

    @JsonProperty("hrv")
    var hrv: Hrv?,

    @JsonProperty("hun")
    var hun: Hun?,

    @JsonProperty("ita")
    var ita: Ita?,

    @JsonProperty("jpn")
    var jpn: Jpn?,

    @JsonProperty("kor")
    var kor: Kor?,

    @JsonProperty("nld")
    var nld: Nld?,

    @JsonProperty("per")
    var per: Per?,

    @JsonProperty("pol")
    var pol: Pol?,

    @JsonProperty("por")
    var por: Por?,

    @JsonProperty("rus")
    var rus: Rus?,

    @JsonProperty("slk")
    var slk: Slk?,

    @JsonProperty("spa")
    var spa: Spa?,

    @JsonProperty("swe")
    var swe: Swe?,

    @JsonProperty("urd")
    var urd: Urd?,

    @JsonProperty("zho")
    var zho: Zho?,

    @JsonProperty("cym")
    var cym: Cym?
)

data class Cym(
    @JsonProperty("official") var official: String?,
    @JsonProperty("common") var common: String?
)