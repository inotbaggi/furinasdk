package me.baggi.furinasdk.utility

import me.baggi.furinasdk.FurinaSDK

fun async(runnable: Runnable) = FurinaSDK.instance.plugin.server.scheduler.runTaskAsynchronously(
    FurinaSDK.instance.plugin,
    runnable
)

fun sync(runnable: Runnable) = FurinaSDK.instance.plugin.server.scheduler.runTask(
    FurinaSDK.instance.plugin,
    runnable
)

fun asyncTimer(runnable: Runnable, delay: Long, period: Long) = FurinaSDK.instance.plugin.server.scheduler.runTaskTimerAsynchronously(
    FurinaSDK.instance.plugin,
    runnable,
    delay,
    period
)

fun timer(runnable: Runnable, delay: Long, period: Long) = FurinaSDK.instance.plugin.server.scheduler.runTaskTimer(
    FurinaSDK.instance.plugin,
    runnable,
    delay,
    period
)

fun later(runnable: Runnable, delay: Long) = FurinaSDK.instance.plugin.server.scheduler.runTaskLater(
    FurinaSDK.instance.plugin,
    runnable,
    delay,
)

fun laterAsync(runnable: Runnable, delay: Long) = FurinaSDK.instance.plugin.server.scheduler.runTaskLaterAsynchronously(
    FurinaSDK.instance.plugin,
    runnable,
    delay,
)