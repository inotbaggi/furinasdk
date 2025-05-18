package me.baggi.furinasdk.utility

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import org.apache.commons.lang3.text.StrSubstitutor

var miniMessage: MiniMessage = MiniMessage.builder().build()

fun String.asComponent() = miniMessage.deserialize(this)

fun String.asComponent(placeholders: MutableMap<String, String> = mutableMapOf()): Component {
    val sub = StrSubstitutor(placeholders , "%", "%")
    val result = sub.replace(this)
    return result.asComponent()
}

fun Component.notItalic() = this.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE)