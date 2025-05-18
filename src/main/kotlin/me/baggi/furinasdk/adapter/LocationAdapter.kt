package me.baggi.furinasdk.adapter

import com.google.gson.*
import org.bukkit.Bukkit
import org.bukkit.Location
import java.lang.reflect.Type

class LocationAdapter : JsonDeserializer<Location>, JsonSerializer<Location> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext?): Location {
        if (!json.isJsonObject) throw JsonParseException("Not a JSON object")

        val obj = json.asJsonObject
        val worldName = obj.getAsJsonPrimitive("world")?.asString
        val x = obj.getAsJsonPrimitive("x")?.asDouble
        val y = obj.getAsJsonPrimitive("y")?.asDouble
        val z = obj.getAsJsonPrimitive("z")?.asDouble
        val yaw = obj.getAsJsonPrimitive("yaw")?.asFloat
        val pitch = obj.getAsJsonPrimitive("pitch")?.asFloat

        if (worldName == null || x == null || y == null || z == null || yaw == null || pitch == null) {
            throw JsonParseException("Malformed location JSON string!")
        }

        val world = Bukkit.getWorld(worldName) ?: throw JsonParseException("Unknown or not loaded world")

        return Location(world, x, y, z, yaw, pitch)
    }

    override fun serialize(src: Location, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonObject().apply {
            addProperty("world", src.world.name)
            addProperty("x", src.x)
            addProperty("y", src.y)
            addProperty("z", src.z)
            addProperty("yaw", src.yaw)
            addProperty("pitch", src.pitch)
        }
    }
}
