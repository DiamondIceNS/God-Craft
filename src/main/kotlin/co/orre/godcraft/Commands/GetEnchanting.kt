package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetEnchanting(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player && sender.hasPermission("GodCraft.Getenchanging")) {
            if (args.size < 0 || args.isNotEmpty()) {
                return false
            }

            if (sender.isOnline) {
                sender.openEnchanting(sender.location, true)
                return true
            }
            return false
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "You can not send that command!")
            return true
        }
    }
}
