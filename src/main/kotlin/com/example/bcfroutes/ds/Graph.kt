package com.example.bcfroutes.ds

/**
 * @author  aokilipa
 */
data class Graph<T>(
    val vertices: Set<T>,
    val edges: Map<T, Set<T>>,
    val weights: Map<Pair<T, T>, Int>
) {
    constructor(weights: Map<Pair<T, T>, Int>) : this(
        vertices = weights.keys.toList().getUniqueValuesFromPairs(),
        edges = weights.keys
            .groupBy { it.first }
            .mapValues { it.value.getUniqueValuesFromPairs { x -> x !== it.key } }
            .withDefault { emptySet() },
        weights = weights
    )
}

fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(): Set<T> = this
    .map { (a, b) -> listOf(a, b) }
    .flatten()
    .toSet()

fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(predicate: (T) -> Boolean): Set<T> = this
    .map { (a, b) -> listOf(a, b) }
    .flatten()
    .filter(predicate)
    .toSet()

fun <T> Graph<T>.createPathTree(start: T): Map<T, T?> {
    val vSet: MutableSet<T> = mutableSetOf() // a subset of vertices, for which we know the true distance

    val delta = this.vertices.associateWith { Int.MAX_VALUE }.toMutableMap()
    delta[start] = 0

    val previous: MutableMap<T, T?> = this.vertices.associateWith { null }.toMutableMap()

    while (vSet != this.vertices) {
        val v: T = delta
            .filter { !vSet.contains(it.key) }
            .minByOrNull { it.value }!!
            .key

        this.edges.getValue(v).minus(vSet).forEach { neighbor ->
            val newPath = delta.getValue(v) + this.weights.getValue(Pair(v, neighbor))

            if (newPath < delta.getValue(neighbor)) {
                delta[neighbor] = newPath
                previous[neighbor] = v
            }
        }

        vSet.add(v)
    }

    return previous.toMap()
}