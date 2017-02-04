package co.orre.goddesscraft

import org.bukkit.plugin.Plugin

class Configuration(val plugin: Plugin) {

    var DEBUG: Boolean = false
    var MAX_BOOM_POWER: Int = 10
    var MAX_AIR: Int = 20
    var MAX_HEALTH: Int = 20
    var MAX_LEVEL: Int = 30
    var MAX_FOOD_LEVEL: Int = 20

    fun load() {
        plugin.reloadConfig()

        DEBUG = plugin.config.getBoolean("debug", false)
        MAX_BOOM_POWER = plugin.config.getInt("max_boom_power", 10)
        MAX_AIR = plugin.config.getInt("max_air", 20)
        MAX_HEALTH = plugin.config.getInt("max_health", 20)
        MAX_LEVEL = plugin.config.getInt("max_level", 30)
        MAX_FOOD_LEVEL = plugin.config.getInt("max_food_level", 20)
    }
}