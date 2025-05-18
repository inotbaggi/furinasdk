package me.baggi.furinasdk

import dev.triumphteam.gui.components.GuiAction
import dev.triumphteam.gui.guis.GuiItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType

fun ItemStack.applyItemMeta(consumer: (itemMeta: ItemMeta) -> Unit): ItemStack {
    val meta = itemMeta ?: return this
    consumer(meta)
    this.itemMeta = meta
    return this
}

fun ItemStack.name(component: Component): ItemStack = applyItemMeta {
    it.displayName(component)
}

fun ItemStack.lore(vararg lore: Component): ItemStack = applyItemMeta {
    it.lore(lore.toList())
}

fun ItemStack.addLoreLine(component: Component): ItemStack = applyItemMeta {
    val currentLore = it.lore() ?: mutableListOf()
    currentLore.add(component)
    it.lore(currentLore)
}


fun ItemStack.hideAttributes(): ItemStack = applyItemMeta {
    it.addItemFlags(*ItemFlag.entries.toTypedArray())
}

fun ItemStack.addItemFlag(itemFlag: ItemFlag): ItemStack = applyItemMeta {
    it.addItemFlags(itemFlag)
}

fun ItemStack.removeItemFlag(itemFlag: ItemFlag): ItemStack = applyItemMeta {
    it.addItemFlags(itemFlag)
}

inline fun <reified T, reified Z : Any> ItemStack.setCustomData(
    key: NamespacedKey,
    type: PersistentDataType<T, Z>,
    value: Z
): ItemStack = applyItemMeta {
    it.persistentDataContainer.set(key, type, value)
}

inline fun <reified P : Any, reified C : Any> ItemStack.getCustomData(
    key: NamespacedKey,
    type: PersistentDataType<P, C>
): C? = itemMeta?.persistentDataContainer?.get(key, type)


fun ItemStack.setStringTag(key: NamespacedKey, value: String): ItemStack = setCustomData(key, PersistentDataType.STRING, value)
fun ItemStack.getStringTag(key: NamespacedKey): String? = getCustomData(key, PersistentDataType.STRING)

fun ItemStack.setBooleanTag(key: NamespacedKey, value: Boolean = true): ItemStack = setCustomData(key, PersistentDataType.BOOLEAN, value)
fun ItemStack.getBooleanTag(key: NamespacedKey): Boolean = getCustomData(key, PersistentDataType.BOOLEAN) ?: false

fun ItemStack.setIntTag(key: NamespacedKey, value: Int): ItemStack = setCustomData(key, PersistentDataType.INTEGER, value)
fun ItemStack.getIntTag(key: NamespacedKey): Int? = getCustomData(key, PersistentDataType.INTEGER)

fun ItemStack.color(leatherColor: Color): ItemStack {
    if (this.itemMeta is LeatherArmorMeta) {
        return applyItemMeta {
            (it as LeatherArmorMeta).setColor(leatherColor)
        }
    }
    return this
}

fun ItemStack.unbreakable(flag: Boolean = true): ItemStack = applyItemMeta {
    it.isUnbreakable = flag
}

fun ItemStack.enchant(type: Enchantment, level: Int, ignoreLevelRestriction: Boolean = true): ItemStack =
    applyItemMeta {
        it.addEnchant(type, level, ignoreLevelRestriction)
    }

fun ItemStack.modelData(id: Int): ItemStack = applyItemMeta {
    it.setCustomModelData(id)
}

fun ItemStack.skullOwner(name: String): ItemStack {
    if (this.itemMeta is SkullMeta) {
        return applyItemMeta {
            (it as SkullMeta).owningPlayer = Bukkit.getOfflinePlayer(name)
        }
    }
    return this
}

fun ItemStack.clearMeta(): ItemStack {
    val type = this.type
    return ItemStack(type, this.amount)
}

fun ItemStack.asGuiItem(action: GuiAction<InventoryClickEvent>? = null) = GuiItem(this, action)

