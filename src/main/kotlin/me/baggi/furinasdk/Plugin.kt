package me.baggi.furinasdk

import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {
    override fun onEnable() {
        FurinaSDK().register(this)
    }
}