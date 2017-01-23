package co.orre.godcraft

import co.orre.godcraft.Commands.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class God : JavaPlugin() {

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

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<String>?): Boolean {
        try {
            val c = command!!.name
            return (Class.forName("net.theharrisoncrafter.Commands." + c.toUpperCase()[0] + c.toLowerCase().substring(1)).getConstructor().newInstance() as CommandExecutor).onCommand(sender, command, label, args)
        } catch (e: Exception) {
            sender!!.sendMessage("Something went wrong (" + e.javaClass + "): " + e.message + "!(" + e.cause + ")")
            sender.sendMessage("Please report this problem to theharrisoncrafter@gmail.com with this error and a list of the steps you took to get this error.")
            e.printStackTrace()
        }

        return false
    }

    fun logDebug(msg: String) {
        if (!configuration.DEBUG) return
        logger.info(msg)
    }
}