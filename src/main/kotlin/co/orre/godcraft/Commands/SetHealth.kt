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

class SetHealth(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.set_health")) {
                var health = 0
                val targetPlayer: Player?

                if (args.size > 2) return false
                when {
                    args.size == 1 && !Util.isParsableToInt(args[0]) -> targetPlayer = Bukkit.getPlayer(args[0])
                    args.size == 1 && Util.isParsableToInt(args[0]) -> {
                        targetPlayer = sender
                        health = args[0].toInt()
                    }
                    args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1]) -> {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        health = args[1].toInt()
                    }
                    else -> return false
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                if (health > targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue) {
                    sender.sendMessage("${CC.RED}That is to much. Please use a number between 0 and " +
                            "${targetPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue}")
                    return true
                }

                targetPlayer.health = health.toDouble()
                sender.sendMessage("${CC.YELLOW}Health set!")
                plugin.logDebug("${sender.name} set ${targetPlayer.name}'s health to $health")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.size != 2) return false
        if (args.size != 2 || Util.isParsableToInt(args[0]) || !Util.isParsableToInt(args[1])) return false

        val targetPlayer = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        val health = args[1].toInt()
        targetPlayer.health = health.toDouble()
        sender.sendMessage("${CC.YELLOW}Health set!")
        plugin.logDebug("Console set ${targetPlayer.name}'s food level to $health")
        return true
    }
}
