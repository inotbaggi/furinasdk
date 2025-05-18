package me.baggi.furinasdk

import me.baggi.furinasdk.annotation.RegisterAsListener
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder
import java.util.logging.Logger

class FurinaSDK {
    val logger = Logger.getLogger("FurinaSDK")

    var debug = false
    private var registered: Boolean = false
    lateinit var plugin: JavaPlugin

    companion object {
        lateinit var instance: FurinaSDK
    }

    fun register(plugin: JavaPlugin, debug: Boolean = false) {
        check(!registered) { "FurinaSDK is already registered!" }

        this.plugin = plugin
        this.debug = debug

        scanListeners(plugin.javaClass.`package`.name)

        instance = this

        registered = true
        logger.info("FurinaSDK registered for ${plugin.name}!")
    }

    fun scanListeners(path: String) {
        val reflections = Reflections(
            ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(path, plugin.javaClass.classLoader))
                .filterInputsBy(FilterBuilder().includePackage(path))
                .setScanners(Scanners.TypesAnnotated)
        )

        reflections.getTypesAnnotatedWith(RegisterAsListener::class.java).forEach { clazz ->
            val listenerInstance = clazz.getDeclaredConstructor().newInstance()
            plugin.server.pluginManager.registerEvents(listenerInstance as org.bukkit.event.Listener, plugin)
            if (debug) logger.info("Registered listener: ${clazz.simpleName}")
        }
    }
}