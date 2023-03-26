import javax.xml.bind.JAXBElement.GlobalScope

interface Handler
{
    suspend fun handleRequest(messageToBeProcessed:String):Handler
}

abstract class AbstractFactory
{
    abstract fun getHandler(handler: String): Handler
}

class FactoryProducer
{
    fun getFactory(choice: String): AbstractFactory
    {
        when(choice){
            "EliteFactory" -> return EliteFactory()
            "HappyWorkerFactory" -> return HappyWorkerFactory()
        }
    }
}

class EliteFactory: AbstractFactory() {
    override fun getHandler(handler: String): Handler
    {
        when(handler)
        {
            "CEOHandler" -> return CEOHandler()
            "ExecutiveHandler" -> return ExecutiveHandler()
            "ManagerHandler" -> return ManagerHandler()
        }
    }
}

class HappyWorkerFactory: AbstractFactory()
{
    override fun getHandler(handler: String): Handler
    {
        return HappyWorkerHandler()
    }
}

class CEOHandler(next1:Handler,next2:Handler)
{
    override suspend fun handleRequest(messageToBeProcessed: String)
    {
        var list=messageToBeProcessed.split(":")
        when(list[0])
        {
            "1" -> {
                println("Response ->  "+messageToBeProcessed)
                GlobalScope.launch{next1.handleRequest(messageToBeProcessed)}
                delay(200)
            }
            else
            {
                GlobalScoper.launch{next2.handleRequest(messageToBeProcessed)}
                delay(200)
            }
        }
    }
}

class ExecutiveHandler(next1:Handler,next2:Handler)
{
    override suspend fun handleRequest(messageToBeProcessed: String)
    {
        var list=messageToBeProcessed.split(":")
        when(list[0])
        {
            "2" -> {
                println("Response ->  "+messageToBeProcessed)
                GlobalScope.launch{next1.handleRequest(messageToBeProcessed)}
                delay(200)
            }
            else
            {
                GlobalScoper.launch{next2.handleRequest(messageToBeProcessed)}
                delay(200)
            }
        }
    }
}

class ManagerHandler(next1:Handler,next2:Handler)
{
    override suspend fun handleRequest(messageToBeProcessed: String)
    {
        var list=messageToBeProcessed.split(":")
        when(list[0])
        {
            "3" -> {
                println("Response ->  "+messageToBeProcessed)
                GlobalScope.launch{next1.handleRequest(messageToBeProcessed)}
                delay(200)
            }
            else
            {
                GlobalScoper.launch{next2.handleRequest(messageToBeProcessed)}
                delay(200)
            }
        }
    }
}

class HappyWorkerHandler(next1:Handler,next2:Handler)
{
    override suspend fun handleRequest(messageToBeProcessed: String)
    {
        var list=messageToBeProcessed.split(":")
        when(list[0])
        {
            "4" -> {
                println("Response ->  "+messageToBeProcessed)
                GlobalScope.launch{next1.handleRequest(messageToBeProcessed)}
                delay(200)
            }
            else
            {
                GlobalScoper.launch{next2.handleRequest(messageToBeProcessed)}
                delay(200)
            }
        }
    }
}

fun main(args:Array<String>)
{
    var prod=FactoryProducer()
    var elite=prod.getFactory("EliteFactory")
    var happy=prod.getFactory("HappyFactory")
    var ceo=elite.getHandler("CEOHandler")
    var manag=elite.getHandler("ManagerHandler")
    var exec=elite.getHandler("ExecutiveHandler")
    var worker=happy.getHandler("HappyWorkerHandler")
    var ceo2=elite.getHandler("CEOHandler")
    var manag2=elite.getHandler("ManagerHandler")
    var exec2=elite.getHandler("ExecutiveHandler")
    var worker2=happy.getHandler("HappyWorkerHandler")
    ceo.next1=ceo2
    ceo.next2=exec
    exec.next1=exec2
    exec.next2=manag
    manag.next1=manag2
    manag.next2=worker
    worker.next1=worker2
    runBlocking
    {
        launch{
            ceo.handleRequest("3:Sa faca managerul")
            exec.handleRequest("4:E pentru worker")
            worker.handleRequest("4:E pentru mine")
            manag.handleRequest("1:E pentru CEO")
        }
    }
}