import jdk.internal.org.objectweb.asm.Handle
import javax.xml.bind.JAXBElement.GlobalScope
import kotlinx.coroutines.*

interface Handler
{
    fun handleRequest(messageToBeProcessed:String)
    {

    }
}

abstract class AbstractFactory
{
    fun getHandler(handle:String):Handle{

    }
}

class FactoryProducer
{
    fun getFactory(choice:String):AbstractFactory
    {

    }
}

class EliteFactory
{
    fun getHandler(handler:String):Handler
    {

    }
}

class HappyWorkerFactory
{
    fun getHandler(handler:String):Handler
    {}
}

class CEOHandler
{
    var next1:Handler?=null
    var next2:Handler? = null
    fun handleRequest(messageToBeProcessed:String)
    {
        var list=messageToBeProcessed.split(":")
        when(list[1])
        {
            "1" -> {
                GlobalScope.launch {
                    println("Request - " + messageToBeProcessed)
                    next1?.handleRequest(messageToBeProcessed)
                }
                delay(200)
            }
            else -> {
                next2?.handleRequest(messageToBeProcessed)
                delay(200)
            }

        }
    }
}

class ExecutiveHandler
{
    var next1:Handler?=null
    var next2:Handler? = null
    var next3:Handler?=null
    fun handleRequest(messageToBeProcessed:String)
    {

    }
}

class ManagerHandler
{
    var next1:Handler?=null
    var next2:Handler? = null
    var next3:Handler?=null
    fun handleRequest(messageToBeProcessed:String)
    {

    }
}

class HappyWorkerHandler
{
    var next1:Handler?=null
    var next2:Handler? = null
    fun handleRequest(messageToBeProcessed:String)
    {

    }
}

fun main(args: Array<String>) {
}