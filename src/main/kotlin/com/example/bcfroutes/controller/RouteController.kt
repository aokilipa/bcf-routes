package com.example.bcfroutes.controller

import com.example.bcfroutes.model.Route
import com.example.bcfroutes.service.RouteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


/**
 * @author  aokilipa
 */
@RestController
@RequestMapping("api/v1")
class RouteController {

    @Autowired
    lateinit var routeService: RouteService;

    @GetMapping("/routing/{origin}/{destination}")
    fun routing(@PathVariable origin: String, @PathVariable destination: String): Mono<ResponseEntity<Route>> {
        return routeService.routing(origin, destination);
    }


    @ExceptionHandler(ResponseStatusException::class, ArrayIndexOutOfBoundsException::class, RuntimeException::class)
    fun handleException(ex: RuntimeException): Mono<ResponseEntity<Map<String, String>>> {
        val errorMessage = if (ex is ResponseStatusException) ex.reason else ex.message
        val httpStatus = if (ex is ResponseStatusException) ex.status else HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(httpStatus).body(mapOf(Pair("error", errorMessage ?: "Something went wrong"))).toMono()
    }
}