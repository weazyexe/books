package exe.weazy

import exe.weazy.extensions.*
import exe.weazy.structures.Graph

fun main() {
    while (true) {
        menu()

        when (readLine()) {
            "0" -> {
                return
            }
            "1" -> {
                val collection = (1..50).toMutableList()
                println(">> in array $collection find index of 9: ${collection.binarySearch(9)}")
            }
            "2" -> {
                val collection = (1..15).shuffled()
                println(">> collection")
                println(">> $collection")
                println(">> sorted")
                println(">> ${collection.selectionSort()}")
            }
            "3" -> {
                val collection = (1..100).toMutableList()
                print(">> sum from 1 to 100 = ${collection.recursionSum()}")
            }
            "4" -> {
                val collection = (1..100).toMutableList()
                print(">> collection size = ${collection.recursionSize()}")
            }
            "5" -> {
                val collection = mutableListOf(6, 2, 6, 91, 3, 0, -42, 5, 1, 543, 23, 612, 82, 1)
                println(">> in collection")
                println(">> $collection")
                println(">> max is ${collection.recursionFindMax(collection.first(), 0)}")
            }
            "6" -> {
                val collection = (0..15).toMutableList()
                println(">> in array $collection find index of 9: ${collection.recursionBinarySearch(9, 0, collection.size - 1)}")
            }
            "7" -> {
                val collection = (1..15).shuffled() as MutableList
                println(">> collection")
                println(">> $collection")
                println(">> sorted")
                println(">> ${collection.quickSort()}")
            }
            "8" -> {
                val graph = makeGraph()

                println("lets find Samantha: ${graph.find("Samantha", "Me")}")
                println("lets find Norton: ${graph.find("Norton", "Me")}")
                println("lets find Thomas: ${graph.find("Thomas", "Me")}")
            }
            "9" -> {
                val graph = makeGraphFromBook()

                println("min distance from start to final: ${graph.minDistance("Start", "Final")}")
            }
            "10" -> {
                val graph = makeGraph()

                println("min distance from Me to Christopher: ${graph.minDistance("Me", "Christopher")}")
                println("min distance from Me to Norton: ${graph.minDistance("Me", "Norton")}")
            }
        }

        println('\n')
    }
}

fun menu() {
    println("grokking algorithms: code implementations")
    println("0. exit")
    println("1. binary search (default)")
    println("2. selection sort")
    println("3. recursion sum of collection elements")
    println("4. recursion collection size")
    println("5. recursion find max in collection")
    println("6. recursion binary search")
    println("7. quick sort")
    println("8. bfs")
    println("9. dijkstra (example from the book)")
    println("10. dijkstra (my example)")
}

fun makeGraph() : Graph<String> {
    val graph = Graph<String>()

    graph.add("Me", mapOf("John" to 5, "Peggy" to 7, "Samantha" to 9))
    graph.add("John", mapOf("Christopher" to 3))
    graph.add("Peggy", mapOf("Norton" to 5))
    graph.add("Samantha", mapOf("Norton" to 8, "Christopher" to 5))
    graph.add("Christopher", mapOf())
    graph.add("Norton", mapOf())

    return graph
}

fun makeGraphFromBook() : Graph<String> {
    val graph = Graph<String>()

    graph.add("Start", mapOf("A" to 6, "B" to 2))
    graph.add("A", mapOf("Final" to 1))
    graph.add("B", mapOf("A" to 3, "Final" to 5))
    graph.add("Final", mapOf())

    return graph
}