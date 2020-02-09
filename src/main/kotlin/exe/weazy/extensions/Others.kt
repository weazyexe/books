package exe.weazy.extensions

fun List<Int>.binarySearch(searched: Int) : Int? {
    var high = this.size - 1
    var low = 0

    while (low <= high) {
        val mid = (high + low) / 2

        when {
            searched == this[mid] -> {
                return mid;
            }
            searched > this[mid] -> {
                low = mid
            }
            else -> {
                high = mid
            }
        }
    }

    return null;
}