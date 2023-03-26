import java.io.File
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class HistoryLogRecord(Commandline:String,StartData:Timestamp): Comparable<HistoryLogRecord>
{
    var Commandline:String=Commandline
    var StartData:Timestamp=StartData
    override fun compareTo(other: HistoryLogRecord): Int {
        return this.StartData.compareTo(other.StartData)
    }
    fun afiseaza()
    {
        println(this.StartData)
        println(this.Commandline)
    }
    override fun toString() :String
    {
        return "%s\n%s".format(this.StartData,this.Commandline)
    }
}

fun inlocuieste(h1:HistoryLogRecord,h2:HistoryLogRecord,MuttableHashMap:HashMap<Timestamp,HistoryLogRecord>)
{
    for (i in MuttableHashMap.keys)
    {
        if(i==h1.StartData)
        {
            MuttableHashMap[i]=h2;
        }
    }

}

fun <T:Comparable<T>> inlocuieste2(h1:T,h2:T,map:HashMap<out Timestamp,T>):HashMap<Timestamp,T>
{
    var ret:HashMap<Timestamp,T> = map as HashMap<Timestamp,T>
    for ((key,value) in map)
    {
        if(value.compareTo(h1)>=0)
        {
            ret[key]=h2
        }
    }
    return ret
}

fun <T: Comparable<T>> compara(h1:T,h2:T): T
{
    if(h1.compareTo(h2)>=0) {
        println("prima e mai recenta")
        return h1
    }
    else {
        println("a doua e mai recenta")
        return h2
    }
}

fun main(args: Array<String>) {
    var content= File("history.log").readLines()
    var date=Date()
    var t = Timestamp(date.time)
    var log: HistoryLogRecord =HistoryLogRecord("",t)
    var log2: HistoryLogRecord =HistoryLogRecord("",t)
    var comanda:String=""
    var sir:String=""
    var time:Timestamp=t
    var nr=0
    var MutableHashMap=HashMap<Timestamp,HistoryLogRecord>()
    content.forEach()
    {
        if(it.contains("Start-Date:")) {
            sir = it.substring(12)
            time=Timestamp(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sir).time)
        }
        if(it.contains("Commandline:")) {
            comanda = it.substring(13)
            log=HistoryLogRecord(comanda, time)
            MutableHashMap[time] = HistoryLogRecord(comanda,time)
        }
    }
    for (i in MutableHashMap.keys)
    {
        println(MutableHashMap[i])
        println()
    }
    for (i in MutableHashMap.keys)
    {
        nr++
        if(nr==47) {
            log2= MutableHashMap[i]!!
        }

    }

    println("Cea mai recenta din cele 2 alese")
    var recent:HistoryLogRecord=compara(log,log2)
    log.afiseaza()
    log2.afiseaza()
    recent.afiseaza()

    println("\nVarianta cu inlocuire")
    //inlocuieste(log,log2,MutableHashMap)
    MutableHashMap=inlocuieste2(log,log2,MutableHashMap)
    println()
    for (i in MutableHashMap.keys)
    {
        println(MutableHashMap[i])
        println()
    }


}