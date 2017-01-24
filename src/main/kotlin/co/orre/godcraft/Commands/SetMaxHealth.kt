package co.orre.godcraft.Commands

import co.orre.godcraft.God
import co.orre.godcraft.Util
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetMaxHealth(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.set_max_health")) {
                var maxHealth = 0
                var targetPlayer: Player? = null

                if (args.size > 2) return false
                else {
                    if (args.isEmpty()) {
                        targetPlayer = sender
                        maxHealth = 20
                    } else if (args.size == 1 && Util.isParsableToInt(args[0])) {
                        targetPlayer = sender
                        maxHealth = args[0].toInt()
                    } else if (args.size == 1 && !Util.isParsableToInt(args[0])) {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        maxHealth = 20
                    } else if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        maxHealth = args[1].toInt()
                    }

                    if (targetPlayer == null) {
                        sender.sendMessage("${CC.RED}That player could not be found!")
                        return true
                    }

                    if (maxHealth > plugin.config.getInt("max_health", 20)) {
                        sender.sendMessage("${CC.RED}That is to much. Please use a number between 0 and " +
                                "${plugin.config.getInt("max_health", 20)}")
                        return true
                    }
                }

                targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue = maxHealth.toDouble()
                sender.sendMessage("${CC.YELLOW}Max health set!")
                plugin.logDebug("${sender.name} set ${targetPlayer.name}'s maximum health to $maxHealth")
                return true
            } else {
                sender.sendMessage("${CC.RED}You do not have permissions to do that!")
                return true
            }
        } else {
            var maxHealth = 0
            var targetPlayer: Player? = null

            if (args.isEmpty() || args.size > 2) return false
            else {
                if (args.size == 1 && !Util.isParsableToInt(args[0])) {
                    targetPlayer = Bukkit.getPlayer(args[0])
                    maxHealth = 20
                } else if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                    targetPlayer = Bukkit.getPlayer(args[0])
                    maxHealth = args[1].toInt()
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }
            }

            targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue = maxHealth.toDouble()
            sender.sendMessage("${CC.YELLOW}Max health set!")
            plugin.logDebug("Console set ${targetPlayer.name}'s maximum health to $maxHealth")
            return true
        }
    }
}
