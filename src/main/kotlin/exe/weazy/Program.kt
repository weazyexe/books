package exe.weazy

fun main() {
    val algos = Algorithms()

    while (true) {
        menu()

        when (readLine()) {
            "0" -> {
                return
            }
            "1" -> {
                val collection = (1..50).toMutableList()
                println(">> in array $collection find index of 9: ${algos.binarySearch(collection, 9)}")
            }
            "2" -> {
                val collection = mutableListOf(6, 2, 6, 91, 3, 0, -42, 5, 1, 543, 23, 612, 82, 1)
                println(">> collection before")
                println(">> $collection")
                println(">> collection after")
                println(">> ${algos.selectionSort(collection)}")
            }
            "3" -> {
                val collection = (1..100).toMutableList()
                print(">> sum from 1 to 100 = ${algos.recursionSum(collection)}")
            }
            "4" -> {
                val collection = (1..100).toMutableList()
                print(">> collection size = ${algos.collectionSize(collection)}")
            }
            "5" -> {
                val collection = mutableListOf(6, 2, 6, 91, 3, 0, -42, 5, 1, 543, 23, 612, 82, 1)
                println(">> in collection")
                println(">> $collection")
                println(">> max is ${algos.recursionFindMax(collection, collection.first(), 0)}")
            }
            "6" -> {
                val collection = (0..15).toMutableList()
                println(">> in array $collection find index of 9: ${algos.recursionBinarySearch(collection, 9, 0, collection.size - 1)}")
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
}