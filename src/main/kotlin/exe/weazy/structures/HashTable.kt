package exe.weazy.structures

import java.util.*
import kotlin.math.abs

class HashTable<K, V> {
    var size = 50
        set(value) {
            field = value
            reorganizeData()
        }

    private var array: Array<LinkedList<Pair<K, V>>?> = arrayOfNulls(size)

    fun get(key: K) : V? {
        val list = array[hash(key)]

        list?.forEach {
            if (it.first == key) {
                return it.second
            }
        }

        return null
    }

    fun set(key: K, value: V) {
        val index = hash(key)
        if (array[index] == null) {
            array[index] = LinkedList()
        } else {
            val list = LinkedList(array[index]!!)
            list.forEachIndexed { i, pair ->
                if (pair.first == key) {
                    array[index]?.set(i, Pair(key, value))
                    return
                }
            }
        }
        array[index]?.add(Pair(key, value))
    }

    fun remove(key: K) {
        val index = hash(key)
        if (array[index] != null) {
            val list = LinkedList(array[index]!!)
            list.forEach { pair ->
                if (pair.first == key) {
                    array[index]?.remove(pair)
                    return
                }
            }
        }

        if (array[index]?.size == 0) {
            array[index] = null
        }
    }

    fun printAll() {
        array.forEach { list ->
            list?.forEach { item ->
                println("${item.first} | ${item.second}")
            }
        }
    }

    private fun reorganizeData() {
        val oldData = array.clone()
        array = arrayOfNulls(size)

        oldData.forEach { list ->
            list?.forEach {
                set(it.first, it.second)
            }
        }
    }

    private fun hash(key: K) = abs(key.hashCode() % size)
}