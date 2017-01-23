package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Lightning(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player && sender.hasPermission("GodCraft.Lightning")) {
            if (args.size >= 2) {
                return false
            }

            val set: Set<Material>? = null
            if (args.size == 1) {
                if (args[0] == "1") {
                    sender.world.strikeLightning(sender.getTargetBlock(set, 10000).location)
                    sender.sendMessage(ChatColor.YELLOW.toString() + "Kaboom!")
                    return true
                } else if (args[0] == "0") {
                    sender.world.strikeLightningEffect(sender.getTargetBlock(set, 10000).location)
                    sender.sendMessage(ChatColor.YELLOW.toString() + "Kaboom!")
                    return true
                }
            } else {
                sender.world.strikeLightningEffect(sender.getTargetBlock(set, 10000).location)
                sender.sendMessage(ChatColor.YELLOW.toString() + "Kaboom!")
                return true
            }
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "You can not send that command!")
            return true
        }
        return false
    }
}