package org.example
import jdk.jfr.consumer.RecordedClass
import kotlinx.coroutines.*
interface NumaratorI
{
    fun attach(r:Receptor){}
    fun detach(r:Receptor){}
    fun notifyR(){}
}
class Numarator(var value:Int,var receptori:MutableList<Receptor>):NumaratorI
{
    override fun attach(r:Receptor)
    {
        receptori.add(r)
    }
    override fun detach(r:Receptor)
    {
        receptori.remove(r)
    }
    override fun notifyR()
    {
        println("se anunta receptorul")
        for(r in receptori)
        {
            r.afiseaza()
        }
    }
    fun change()
    {
        value=value+1;
        println("s-a schimbat valoarea")
        for (r in receptori)
        {
            this.notifyR()
        }
    }

}
interface ReceptorI
{
    fun afiseaza(){}
}
class Receptor(var num:Numarator):ReceptorI
{
    override fun afiseaza()
    {
        println(num.value)
    }
}
fun main(args: Array<String>)= runBlocking{
    var list= mutableListOf<Receptor>()
    var num:Numarator= Numarator(0,list)
    var rec=Receptor(num)
    num.attach(rec)
    var op=async {
        num.change()
    }
    var op2=async {
        delay(200)
        num.change()
    }
    op2.await()
    op.await()
}

