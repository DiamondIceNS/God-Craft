package co.orre.goddesscraft

import co.orre.goddesscraft.Commands.*
import org.bukkit.plugin.java.JavaPlugin

class Goddess : JavaPlugin() {

    val configuration = Configuration(this)

    override fun onEnable() {
        this.saveDefaultConfig()
        config.options().copyDefaults(true)
        config.set("version", description.version)
        saveConfig()
        configuration.load()

        getCommand("boom").executor = Boom(this)
        getCommand("burn").executor = Burn(this)
        getCommand("feed").executor = Feed(this)
        getCommand("getcrafting").executor = GetCrafting(this)
        getCommand("getenchanting").executor = GetEnchanting(this)
        getCommand("getenderchest").executor = GetEnderChest(this)
        getCommand("getinventory").executor = GetInventory(this)
        getCommand("goddesscraft").executor = GoddessCraft(this)
        getCommand("heal").executor = Heal(this)
        getCommand("lightning").executor = Lightning(this)
        getCommand("murder").executor = Murder(this)
        getCommand("nickname").executor = Nickname(this)
        getCommand("ping").executor = Ping(this)
        getCommand("setfoodlevel").executor = SetFoodLevel(this)
        getCommand("sethealth").executor = SetHealth(this)
        getCommand("setlevel").executor = SetLevel(this)
        getCommand("setmaxair").executor = SetMaxAir(this)
        getCommand("setmaxhealth").executor = SetMaxHealth(this)
        getCommand("starve").executor = Starve(this)
        getCommand("throw").executor = Throw(this)
        getCommand("troll").executor = Troll(this)
    }

    override fun onDisable() {}

    fun logDebug(msg: String) {
        if (!configuration.DEBUG) return
        logger.info(msg)
    }

    fun reload() {
        reloadConfig()
        configuration.load()
    }
}