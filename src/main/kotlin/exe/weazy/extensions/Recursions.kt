package exe.weazy.extensions

fun List<Int>.recursionSum(): Int {
    if (this.isEmpty()) {
        return 0
    }

    return this.first() + this.subList(1, this.size).recursionSum()
}

fun List<Int>.recursionSize(): Int {
    if (this.isEmpty()) {
        return 0
    }

    (this as MutableList).removeAt(0)
    return 1 + this.recursionSize()
}

fun List<Int>.recursionFindMax(max: Int, index: Int): Int {
    if (index == this.size - 1) {
        return max
    }

    if (this[index] > max) {
        return this.recursionFindMax(this[index], index + 1)
    }

    return this.recursionFindMax(max, index + 1)
}

fun List<Int>.recursionBinarySearch(searched: Int, low: Int, high: Int) : Int {
    val mid = (high + low) / 2

    if (searched == this[mid]) {
        return mid
    }

    if (searched > this[mid]) {
        return this.recursionBinarySearch(searched, mid + 1, high)
    }

    return this.recursionBinarySearch(searched, low, mid - 1)
}