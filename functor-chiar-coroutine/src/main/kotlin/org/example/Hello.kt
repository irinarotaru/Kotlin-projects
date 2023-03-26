package org.example
import kotlinx.coroutines.*

fun Test(map:MutableMap<Int,String>):MutableMap<Int,String>
{
    for (key in map.keys)
    {
        var s=map[key]
        map[key]="Test "+s
    }
    return map
}
fun MutableMap<Int,String>.toPascalCase(): MutableMap<Int,String>
{
    for (key in this.keys)
    {
        var comp=this[key]?.split(" ")
        var res=""
        for (c in comp!!)
        {
            res+=c.capitalize()
        }
        this[key]=res
    }
    return this
}
class MapFunctor<T>(var map:T)
{
    fun map(function:(T)->T):MapFunctor<T> {
        return MapFunctor((function(map)))
    }
}
fun main(args: Array<String>)= runBlocking {
    var map= mutableMapOf<Int,String>(1 to "buna ce faci", 2 to "te salut", 3 to "hei si tie", 4 to "happy hello")
    var m=async(CoroutineName("v1corooutine")){
        MapFunctor(map).map(::Test)
    }
    var m2=async(CoroutineName("v2coroutine")){
        delay(400)
        MapFunctor(map.toPascalCase())
    }
    println(m.await().map)
    println(m2.await().map)
}

