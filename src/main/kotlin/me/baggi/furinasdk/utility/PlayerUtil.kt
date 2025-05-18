package me.baggi.furinasdk.utility

import me.baggi.furinasdk.FurinaSDK
import org.bukkit.entity.Player

fun onlinePlayers() = FurinaSDK.instance.plugin.server.onlinePlayers

fun broadcast(text: String) {
    onlinePlayers().forEach {
        it.sendMessage(text.asComponent())
    }
}

fun broadcastActionBar(text: String) {
    onlinePlayers().forEach {
        it.sendActionBar(text.asComponent())
    }
}

fun Player.hideForAll() {
    onlinePlayers().forEach {
        it.hidePlayer(FurinaSDK.instance.plugin, this)
    }
}

fun Player.showForAll() {
    onlinePlayers().forEach {
        it.showPlayer(FurinaSDK.instance.plugin, this)
    }
}