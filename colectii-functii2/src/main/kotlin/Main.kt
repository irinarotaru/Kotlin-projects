
fun main(args: Array<String>) {
    var A= listOf<Int>(4,5,2,17,1,0)
    var B= listOf<Int>(2,3,5,9,8,4)
    var BiA=B.filter { A.contains(it) }
    println(BiA)
    var AUB=A+B.filter { !A.contains(it) }
    println(AUB)
    var AxB=AUB.map{ itA -> BiA.map { itB -> Pair(itA,itB) }}.flatMap { it.distinct()}
    println(AxB)
    var dict=AxB.groupBy { it.first }.map { Pair(it.key,it.value.map { index->index.second }) }.toMap()
    println(dict)
}