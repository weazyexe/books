package exe.weazy.structures

import java.util.*
import kotlin.collections.HashMap

class Graph() {

    private var vertexes: HashMap<String, List<String>> = HashMap()

    constructor(vertexes: HashMap<String, List<String>>) : this() {
        this.vertexes = vertexes.toMap() as HashMap<String, List<String>>
    }

    fun add(value: String, children: List<String>) {
        vertexes[value] = children
    }

    fun remove(value: String) {
        vertexes.remove(value)
    }

    fun find(value: String, startValue: String, method: FindMethod = FindMethod.BFS) : Boolean {
        if (method == FindMethod.BFS) {
            val searched = LinkedList<String>()
            val desired = ArrayDeque<String>(vertexes[startValue])

            while (desired.isNotEmpty()) {
                val v = desired.pop()

                if (!searched.contains(v)) {
                    if (v != value) {
                        searched.add(v)
                        desired.addAll(vertexes[v] as Iterable<String>)
                    } else {
                        return true
                    }
                }
            }
        }

        return false
    }

    enum class FindMethod {
        BFS, DFS
    }
}