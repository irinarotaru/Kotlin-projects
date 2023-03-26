package org.example
import kotlinx.coroutines.*

import kotlin.properties.Delegates
var myIntEven:Int by Delegates.vetoable(0) {
        property, oldValue, newValue ->
    println("${property.name} $oldValue -> $newValue")
    newValue%2==0
}

class Bariera
{
    companion object{
        private val lock=java.lang.Object()
    }
    fun block()
    {
        synchronized(lock)
        {
            lock.wait()
        }
    }
    fun release()
    {
        synchronized(lock)
        {
            lock.notify()
        }
    }
    fun releaseAll()
    {
        synchronized(lock)
        {
            lock.notifyAll()
        }
    }
}
fun main(args: Array<String>)= runBlocking{
    var map= hashMapOf<Int,Int>(1 to 4,2 to 5,3 to 7,4 to 9, 5 to 8, 6 to 11,7 to 23,8 to 44)
    var list= mutableListOf<Int>()
    var barrier=Bariera()
    for(i in map.keys)
    {
        if(i%2==1) {
            var a=async {
                map[i]!! + map[i+1]!!
            }
            list.add(a.await())
            //barrier.block()
            //barrier.release()
        }
    }
    //barrier.releaseAll()
    println(list)
    myIntEven = 6
    myIntEven = 3
    println("myIntEven:$myIntEven")

}

