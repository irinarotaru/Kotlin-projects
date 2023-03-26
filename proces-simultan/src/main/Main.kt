package com.pp.laborator
fun main(args: Array<String>){
    object : Thread() {
        override fun run() {
            println("Sunt in thread-ul singleton ${Thread.currentThread()}")}
    }.start()
    val t1=SimpleThread()
    t1.run()
    val t2=SimpleRunnable()
    t2.run()
    val thread = Thread {
        println("Thread lambda ${Thread.currentThread()} s-a
                executat.")
    }
    thread.start()
}
class SimpleThread: Thread() {
    public override fun run() {
        println("Instanta clasei derivate din Thread ${Thread.currentThread()} s-a executat.")
    }
}
class SimpleRunnable: Runnable {
    public override fun run() {
        println("Instanta clasei care implementeaza Runnable ${Thread.currentThread()} s-a executat.")
    }
}