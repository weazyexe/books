package exe.weazy

import kotlin.random.Random

class Algorithms {
    fun binarySearch(collection: List<Int>, searched: Int) : Int? {
        var high = collection.size - 1
        var low = 0

        while (low <= high) {
            val mid = (high + low) / 2

            when {
                searched == collection[mid] -> {
                    return mid;
                }
                searched > collection[mid] -> {
                    low = mid
                }
                else -> {
                    high = mid
                }
            }
        }

        return null;
    }

    fun selectionSort(collection: MutableList<Int>) : ArrayList<Int> {
        val result = arrayListOf<Int>()
        val size = collection.size

        for (i in 0 until size) {
            val min = findMin(collection)
            result.add(min)
            collection.remove(min)
        }

        return result
    }

    fun recursionSum(collection: List<Int>) : Int {
        if (collection.isEmpty()) {
            return 0
        }

        return collection.first() +
                recursionSum(collection.subList(1, collection.size))
    }

    fun collectionSize(collection: MutableList<Int>) : Int {
        if (collection.isEmpty()) {
            return 0
        }

        collection.removeAt(0)
        return 1 + collectionSize(collection)
    }

    fun recursionFindMax(collection: MutableList<Int>, max: Int, index: Int) : Int {
        if (index == collection.size - 1) {
            return max
        }

        if (collection[index] > max) {
            return recursionFindMax(collection, collection[index], index + 1)
        }

        return recursionFindMax(collection, max, index + 1)
    }

    fun recursionBinarySearch(collection: List<Int>, searched: Int, low: Int, high: Int) : Int {
        val mid = (high + low) / 2

        if (searched == collection[mid]) {
            return mid
        }

        if (searched > collection[mid]) {
            return recursionBinarySearch(collection, searched, mid + 1, high)
        }

        return recursionBinarySearch(collection, searched, low, mid - 1)
    }

    fun quickSort(collection: MutableList<Int>) : MutableList<Int> {
        if (collection.size < 2) {
            return collection
        }

        if (collection.size == 2) {
            if (collection[0] <= collection[1]) {
                return collection
            }

            return mutableListOf(collection[1], collection[0])
        }

        val pivot = collection[Random.nextInt(0, collection.size - 1)]

        val less = collection.filter { it < pivot } as MutableList
        val greater = collection.filter { it > pivot } as MutableList

        return (quickSort(less) + mutableListOf(pivot) + quickSort(greater)) as MutableList
    }

    private fun findMin(array: List<Int>) : Int {
        var min = array.first()
        array.forEach {
            if (it < min) {
                min = it
            }
        }

        return min
    }
}