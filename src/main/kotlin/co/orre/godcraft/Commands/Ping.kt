package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Ping(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("GodCraft.Ping")) {
                sender.sendMessage(ChatColor.GREEN.toString() + "Pong")
                return true
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You do not have permissions to do that!")
                return true
            }
        } else {
            sender.sendMessage(ChatColor.YELLOW.toString() + "Why would you do this?")
            return true
        }
    }
}
