package org.example
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.*

interface Mediator
{
    fun comunica(mesaj:String,from: String,to:String)
}
class Furnica(val name:String,val mediator:FurnicaMediator)
{
    fun comunica(mesaj:String,to:String)
    {
        mediator.comunica(mesaj,this.name,to)
    }
    fun primeste(mesaj:String)
    {
        println("[$name] a primit mesajul: $mesaj")
    }
}

class FurnicaMediator:Mediator
{
    override fun comunica(mesaj: String, from: String, to: String) {
        println("[$from] a trimis mesajul $mesaj catre [$to]")
    }
}

fun main(args: Array<String>)=runBlocking {
    val furnicaMesager: FurnicaMediator =FurnicaMediator()
    val furnica1=async(CoroutineName("v1coroutine"))
    {
        Furnica("Furnica sefa", furnicaMesager)
    }
    val furnica2=async(CoroutineName("v2coroutine"))
    {
        delay(200)
        Furnica("Furnica muncitoare",furnicaMesager)
    }
    val furnica3=async(CoroutineName("v1coroutine"))
    {
        Furnica("Furnica lenesa", furnicaMesager)
    }
    furnica1.await().comunica("Mancare","Furnica lenesa")
    furnica1.await().comunica("Pericol","Furnica muncitoare")
    furnica2.await().comunica("Drum bun","Furnica sefa")
    furnica3.await().comunica("Ajutor","Furnica sefa")

}

