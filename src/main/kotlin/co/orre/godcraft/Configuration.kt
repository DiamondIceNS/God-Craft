package co.orre.godcraft

import org.bukkit.plugin.Plugin

class Configuration(val plugin: Plugin) {

    var DEBUG = false
    var MAX_BOOM_POWER: Int = 10
    var MAX_AIR: Int = 20
    var MAX_HEALTH: Int = 20
    var MAX_LEVEL: Int = 20
    var MAX_FOOD_LEVEL: Int = 20

    fun load() {
        plugin.reloadConfig()

        DEBUG = plugin.config.getBoolean("debug", false)
        MAX_BOOM_POWER = plugin.config.getInt("maxboompower", 10)
        MAX_AIR = plugin.config.getInt("maxair", 20)
        MAX_HEALTH = plugin.config.getInt("maxhealth", 20)
        MAX_LEVEL = plugin.config.getInt("maxlevel", 20)
        MAX_FOOD_LEVEL = plugin.config.getInt("maxfoodlevel", 20)
    }
}