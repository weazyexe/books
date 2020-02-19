package exe.weazy.structures

import java.util.*
import kotlin.collections.HashMap

class Graph<T>() {

    private var vertexes: HashMap<T, Map<T, Int>> = HashMap()

    constructor(vertexes: HashMap<T, Map<T, Int>>) : this() {
        this.vertexes = vertexes.toMap() as HashMap<T, Map<T, Int>>
    }

    /**
     * Добавить вершину в граф
     * @param value значение вершины
     * @param children потомки вершины
     */
    fun add(value: T, children: Map<T, Int>) {
        vertexes[value] = children
    }

    /**
     * Удалить вершину из графа
     * @param value удаляемая вершина
     */
    fun remove(value: T) {
        vertexes.remove(value)
    }

    /**
     * BFS (поиск в ширину)
     * @param value искомое значение
     * @param startValue с какой вершины начинаем поиск
     * @return true - value есть в графе, false в обратном случае
     */
    fun find(value: T, startValue: T) : Boolean {
        val searched = LinkedList<T>()  // Вершины, где уже побывали
        val desired = ArrayDeque<T>(vertexes[startValue]?.keys) // Вершины, в которых нужно побывать

        // Пока посетили не все вершины
        while (desired.isNotEmpty()) {
            val v = desired.pop() // Тянем первую из очереди

            // Проверяем, посещали ли её
            if (!searched.contains(v)) {
                // Если это не искомая вершина
                if (v != value) {
                    searched.add(v)
                    desired.addAll(vertexes[v]?.keys as Iterable<T>)
                } else {
                    // We did it
                    return true
                }
            }
        }

        // :(
        return false
    }

    /**
     * Алгоритм Дейкстры - поиск кратчайшего пути в графе
     * @param start от какой вершины
     * @param final до какой вершины считать расстояние
     * @return минимальное расстояние
     */
    fun minDistance(start: T, final: T) : Int {
        val costs = makeCosts(start)    // Расстояния до вершин от стартовой
        val parents = makeParents(start)    // Родители всех вершин
        val processed = mutableListOf<T>()  // Обработанные вершины
        var node = findLowestCostVertex(costs, processed)   // Текущая вершина

        // Пока вершина существует
        while (node != null) {
            val cost = costs.getValue(node) // Берем расстояние от стартовой до текущей вершины
            val neighbours = vertexes[node] // Берем ее соседей

            // Идем по всем соседям вершины
            neighbours?.keys?.forEach {
                val newCost = cost + neighbours.getValue(it)    // Считаем новое расстояние

                // Посчитанное расстояние ближе, чем текущее записанное - обновляем
                if (costs.getValue(it) > newCost) {
                    costs[it] = newCost
                    parents[it] = node
                }
            }
            processed.add(node) // Добавляем вершину в обработанные
            node = findLowestCostVertex(costs, processed)   // Ищем следующую необработанную вершину
        }

        // Возвращаем полученное минимальное расстояние
        return costs.getValue(final)
    }

    /**
     * Поиск вершины, до которой расстояние минимально
     * @param children дочерние элементы вершины
     * @param processed посещённые вершины
     * @return вершина, расстояние до которой минимально или null
     */
    private fun findLowestCostVertex(children: Map<T, Int>?, processed: List<T>) : T? {
        return children
            ?.filter { !processed.contains(it.key) }
            ?.minBy { it.value }
            ?.key
    }

    /**
     * TODO: переделать
     * Посчитать первоначальные расстояния от value до остальных вершин
     * @param value от какой вершины считаем
     * @return хеш-таблица <Вершина> - <Расстояние до неё>
     */
    private fun makeCosts(value: T) : HashMap<T, Int> {
        val infinity = Int.MAX_VALUE
        val map = HashMap<T, Int>()
        val v = vertexes[value]

        if (v != null) {
            vertexes.keys.forEach {
                if (v.containsKey(it) && it != value) {
                    map[it] = v.getValue(it)
                } else if (it != value) {
                    map[it] = infinity
                }
            }
            return map
        }

        return hashMapOf()
    }

    /**
     * TODO: переделать
     * Создание первоначальных родителей у вершин
     * @param value от какой вершины считаем
     * @return хеш-таблица <Вершина> - <Родитель вершины-ключа>
     */
    private fun makeParents(value: T) : HashMap<T, T?> {
        val map = HashMap<T, T?>()
        val v = vertexes[value]

        if (v != null) {
            vertexes.keys.forEach {
                if (v.containsKey(it) && it != value) {
                    map[it] = value
                } else if (it != value) {
                    map[it] = null
                }
            }
            return map
        }

        return hashMapOf()
    }
}