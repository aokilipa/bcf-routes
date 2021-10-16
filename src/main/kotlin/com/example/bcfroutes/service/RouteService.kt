package com.example.bcfroutes.service

import com.example.bcfroutes.ds.Graph
import com.example.bcfroutes.ds.createPathTree
import com.example.bcfroutes.model.Countries
import com.example.bcfroutes.model.Route
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono


/**
 * @author  aokilipa
 */
@Service
class RouteService {

    @Autowired
    lateinit var webClient: WebClient.Builder

    // strategy for deserialization since response Content type is  'text/plain'
    val exchangeStrategies = ExchangeStrategies.builder().codecs { configurer ->
        val mapper = JsonMapper.builder()
            .addModule(KotlinModule(strictNullChecks = true))
            .build()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val decoder = Jackson2JsonDecoder(mapper, MimeTypeUtils.parseMimeType(MediaType.TEXT_PLAIN_VALUE))
        decoder.maxInMemorySize = 16 * 1024 * 1024 // increase byte buffer limit
        configurer.customCodecs().registerWithDefaultConfig(decoder)
    }.build()


    fun routing(origin: String, destination: String): Mono<ResponseEntity<Route>> {
        return fetchData().exchangeToMono { response ->
            if (response.statusCode().is2xxSuccessful) {
                return@exchangeToMono response.bodyToMono(object : ParameterizedTypeReference<List<Countries?>?>() {})
                    .flatMap { countries ->
                        val routes = processResponse(countries, origin, destination)
                        if (routes.isNullOrEmpty() || routes.size == 1) {
                            return@flatMap Mono.error(
                                ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "No Routes Found"
                                )
                            )
                        }
                        return@flatMap Mono.just(ResponseEntity.ok().body(Route(routes)))
                    }
            }
            return@exchangeToMono Mono.error(
                ResponseStatusException(
                    response.statusCode(),
                    "API Error: ${response.statusCode().reasonPhrase}"
                )
            )
        }

    }

    /**
     * process api response
     * */
    fun processResponse(countries: List<Countries?>?, origin: String, destination: String): List<String>? {
        val weight = HashMap<Pair<String, String>, Int>()
        val weightList = countries
            ?.map { c ->
                c?.borders?.associate {
                    // set weight to 1 since its unweighted graph
                    Pair(c.cca3!!, it) to 1
                }
            }

        // flatten weightList
        weightList?.forEach {
            weight.putAll(it.orEmpty())
        }

        return routing(Graph(weight).createPathTree(origin), origin, destination)
    }

    /**
     * Make network request
     * */
    fun fetchData(): WebClient.RequestHeadersSpec<*> {
        return webClient.baseUrl("https://raw.githubusercontent.com")
            .exchangeStrategies(exchangeStrategies)
            .build()
            .get()
            .uri("/mledoze/countries/master/countries.json")
            .accept(MediaType.APPLICATION_JSON)
    }

    fun <T> routing(routeTree: Map<T, T?>, start: T, end: T): List<T> {
        fun pathTo(start: T, end: T): List<T> {
            if (routeTree[end] == null) return listOf(end)
            return listOf(pathTo(start, routeTree[end]!!), listOf(end)).flatten()
        }

        return pathTo(start, end)
    }

}