package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.ChatColor as CC

class Burn(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("GodCraft.Burn")) {
                val targetPlayer: Player?
                if (args.size >= 2) {
                    return false
                } else {
                    if (args.isEmpty()) {
                        targetPlayer = sender
                    } else {
                        targetPlayer = sender.getServer().getPlayer(args[0])
                    }
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                targetPlayer.fireTicks = 20000
                sender.sendMessage("${CC.YELLOW}Burn!")
                plugin.logDebug("${sender.name} burned ${targetPlayer.name}")
                return true
            } else {
                sender.sendMessage("${CC.RED}You do not have permissions to do that!")
                return true
            }
        } else {
            val targetPlayer: Player?
            if (args.isEmpty() || args.size > 1) {
                return false
            } else {
                targetPlayer = Bukkit.getPlayer(args[0])
            }

            if (targetPlayer == null) {
                sender.sendMessage("${CC.RED}That player is not online!")
                return true
            }

            targetPlayer.fireTicks = 20000
            sender.sendMessage("${CC.YELLOW}Burn!")
            plugin.logDebug("Console burned ${targetPlayer.name}")
            return true
        }
    }

}