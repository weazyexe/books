package exe.weazy.extensions

import kotlin.random.Random

fun List<Int>.quickSort() : List<Int> {
    if (this.size < 2) {
        return this
    }

    if (this.size == 2) {
        if (this[0] <= this[1]) {
            return this
        }

        return mutableListOf(this[1], this[0])
    }

    val pivot = this[Random.nextInt(0, this.size - 1)]

    val less = this.filter { it.toDouble() < pivot }
    val greater = this.filter { it.toDouble() > pivot }

    return (less.quickSort() + mutableListOf(pivot) + greater.quickSort())
}

fun List<Int>.selectionSort() : List<Int> {
    val result = arrayListOf<Int>()
    val size = this.size

    for (i in 0 until size) {
        val min = findMin(this)
        result.add(min)
        (this as MutableList).remove(min)
    }

    return result
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