package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetInventory(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player && sender.hasPermission("GodCraft.GetInventory")) {
            val targetPlayer: Player?

            if (args.size > 1) {
                return false
            }

            if (args.isEmpty()) {
                targetPlayer = sender
            } else {
                targetPlayer = Bukkit.getPlayer(args[0])
            }

            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                return true
            }

            sender.openInventory(targetPlayer.inventory)
            return true
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "You can not send that command!")
            return true
        }
    }
}
