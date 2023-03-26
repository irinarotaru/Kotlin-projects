fun main(args: Array<String>) {
    var A= mutableListOf<Int>(5,2,3)
    var B= mutableListOf<Int>(9,5,3)
    var AXB=A.map { itA-> B.map { itB -> Pair(itA,itB) }}.flatMap { it.distinct() }
    println(AXB)
    var BXA=B.map { itB-> A.map { itA -> Pair(itB,itA) }}.flatMap { it.distinct() }
    println(BXA)
    var reun=AXB.filter { BXA.contains(it) }
    println()
    println(reun)
    var dict=reun.groupBy { it.first }.map { Pair(it.key,it.value.map { index->index.second }) }.toMap()
    println(dict)
}