package ru.savenkov.homework

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Timer(private val timeListener: (String) -> Unit) {
    private var executor: ScheduledExecutorService? = null
    private var count: Long = -1
    private var remainingTime: Long = 0
    private var isStopped = true
    private var future: ScheduledFuture<*>? = null

    fun start() {
        isStopped = false
        executor = Executors.newSingleThreadScheduledExecutor()
        future = executor!!.scheduleAtFixedRate({
            count++
            timeListener.invoke(timeToString())
        }, remainingTime, 1000, TimeUnit.MILLISECONDS)
    }

    fun cancel() {
        count = -1
        timeListener.invoke(timeToString())
        stop()
        remainingTime = 0
    }

    fun isStopped() = isStopped

    fun stop() {
        if (executor == null) return
        isStopped = true
        remainingTime = future?.getDelay(TimeUnit.MILLISECONDS)!!
        future?.cancel(true)
        executor!!.shutdown()
    }

    fun timeToString(): String {
        var sec = count
        if (sec == (-1).toLong()) sec = 0
        val min = sec / 60
        sec -= min * 60
        return when {
            sec < 10 && min < 10 -> "0$min:0$sec"
            sec < 10 && min >= 10 -> "$min:0$sec"
            sec >= 10 && min < 10 -> "0$min:$sec"
            else -> "$min:$sec"
        }
    }
}