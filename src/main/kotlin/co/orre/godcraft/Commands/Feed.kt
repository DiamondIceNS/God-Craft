package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Feed(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("GodCraft.Feed")) {
                val targetPlayer: Player?

                if (args.size > 1) {
                    return false
                } else {
                    if (args.isEmpty()) {
                        targetPlayer = sender
                    } else {
                        targetPlayer = sender.getServer().getPlayer(args[0])
                    }
                }

                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                    return true
                }

                targetPlayer.foodLevel = 20
                sender.sendMessage(ChatColor.YELLOW.toString() + "Yum!")
                return true
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You do not have permissions to do that!")
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
                sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                return true
            }

            targetPlayer.foodLevel = 20
            sender.sendMessage(ChatColor.YELLOW.toString() + "Burn!")
            return true
        }
    }

}