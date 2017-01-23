package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetEnderChest(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player && sender.hasPermission("GodCraft.GetEnderChest")) {
            val targetPlayer: Player?
            val version = Bukkit.getBukkitVersion()

            if (version.startsWith("1")) {
                version.replaceFirst("1.".toRegex(), "")
                version.substring(0, 1)
                if (Integer.parseInt(version.substring(0, 1)) < 3) {
                    sender.sendMessage("This version of minecraft is too old for that!")
                    return true
                }
            }

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

            sender.openInventory(targetPlayer.enderChest)
            return true
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "You can not send that command!")
            return true
        }
    }
}
