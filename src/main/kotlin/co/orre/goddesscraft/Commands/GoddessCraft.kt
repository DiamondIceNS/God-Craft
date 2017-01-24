package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import co.orre.goddesscraft.Util
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.ChatColor as CC

class GoddessCraft(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.goddess_craft")) {
                if (args.isEmpty() || args.size > 2) return false
                when (args[0]) {
                    "reload" -> {
                        if (args.size != 1) {
                            sender.sendMessage("Usage: /goddesscraft reload")
                            return true
                        }
                        plugin.reload()
                        sender.sendMessage("${CC.GREEN}Config reloaded!")

                        plugin.logDebug("${sender.name} reloaded config")
                        return true
                    }
                    "debug" -> {
                        if (args.size != 2 || !Util.isParsableToBoolean(args[1])) {
                            sender.sendMessage("Usage: /goddesscraft debug [true|false]")
                            return true
                        }
                        val setDebug = args[1].toBoolean()
                        plugin.configuration.DEBUG = setDebug
                        plugin.config.set("debug", setDebug)
                        plugin.saveConfig()
                        if (setDebug) {
                            sender.sendMessage("${CC.GREEN}Debug enabled")
                            plugin.logDebug("${sender.name} enabled debugging")
                        }
                        else {
                            sender.sendMessage("${CC.GREEN}Debug disabled")
                            plugin.logDebug("${sender.name} disabled debugging, although this message shouldn't ever appear")
                        }
                        return true
                    }
                    else -> return false
                }
            }
            plugin.logDebug("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.isEmpty() || args.size > 2) return false
        when (args[0]) {
            "reload" -> {
                if (args.size != 1) {
                    sender.sendMessage("Usage: /goddesscraft reload")
                    return true
                }
                plugin.reload()
                sender.sendMessage("${CC.GREEN}Config reloaded!")
                plugin.logDebug("Console reloaded config")
                return true
            }
            "debug" -> {
                if (args.size != 2 || !Util.isParsableToBoolean(args[1])) {
                    sender.sendMessage("Usage: /goddesscraft debug [true|false]")
                    return true
                }
                val setDebug = args[1].toBoolean()
                plugin.configuration.DEBUG = setDebug
                plugin.config.set("debug", setDebug)
                plugin.saveConfig()
                if (setDebug) {
                    sender.sendMessage("${CC.GREEN}Debug enabled")
                    plugin.logDebug("Console enabled debugging")
                }
                else {
                    sender.sendMessage("${CC.GREEN}Debug disabled")
                    plugin.logDebug("Console disabled debugging, although this message shouldn't ever appear")
                }
                return true
            }
            else -> return false
        }
    }
}