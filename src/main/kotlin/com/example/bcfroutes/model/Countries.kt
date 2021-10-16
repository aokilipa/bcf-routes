package com.example.bcfroutes.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Countries(
    @JsonProperty("name") var name: Name?,

    @JsonProperty("tld") var tld: List<String>?,

    @JsonProperty("cca2") var cca2: String?,

    @JsonProperty("ccn3") var ccn3: String?,

    @JsonProperty("cca3") var cca3: String?,

    @JsonProperty("cioc") var cioc: String?,

    @JsonProperty("independent") var independent: Boolean?,

    @JsonProperty("status") var status: String?,

    @JsonProperty("unMember") var unMember: Boolean?,

    @JsonProperty("currencies") var currencies: Currencies?,

    @JsonProperty("idd") var idd: Idd?,

    @JsonProperty("capital") var capital: List<String>?,

    @JsonProperty("altSpellings") var altSpellings: List<String>?,

    @JsonProperty("region") var region: String?,

    @JsonProperty("subregion") var subregion: String?,

    @JsonProperty("languages") var languages: Languages?,

    @JsonProperty("translations") var translations: Translations?,

    @JsonProperty("latlng") var latlng: List<Double>?,

    @JsonProperty("landlocked") var landlocked: Boolean?,

    @JsonProperty("borders") var borders: List<String>?,

    @JsonProperty("area") var area: Int?,

    @JsonProperty("flag") var flag: String?,

    @JsonProperty("demonyms") var demonyms: Demonyms?
)