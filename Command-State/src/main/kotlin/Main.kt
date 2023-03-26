interface State
{
    fun handle(context:Context)
}
class StareFericit:State
{
    override fun handle(context:Context)
    {
        println("Studentul este fericit")
        context.setState(this)
    }
}
class StareNefericit:State
{
    override fun handle(context: Context) {
        println("Studentul este nefericit")
        context.setState(this)
    }
}
class Context
{
    var stare:State=StareNefericit()
    fun setState(state:State)
    {
        this.stare=state
    }
}
interface Command
{
    fun execute()
}
class Receiver(state: State)
{
    var stare=state
    var context=Context()
    fun action()
    {
        println("Schimba starea")
        stare.handle(context)
    }
}
class ColegaCommand(receiver: Receiver):Command
{
    var receiver=receiver
    override fun execute() {
        println("Se executa comanda")
        receiver.action()
    }
}
class Invoker
{
    lateinit var acommand:Command
    fun setCommand(command: Command)
    {
        acommand=command
    }
    fun performAction()
    {
        acommand.execute()
    }
}
class Student(invoker:Invoker,state: State)
{
    var reveiver=Receiver(state)
    init {
        val colega=ColegaCommand(reveiver)
        invoker.setCommand(colega)
    }
}
fun main(args: Array<String>) {
    val invoker=Invoker()
    var state=StareNefericit()
    var state2=StareFericit()
    Student(invoker,state)
    invoker.performAction()
    Student(invoker,state2)
    invoker.performAction()
}