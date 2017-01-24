package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Murder(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.murder")) {
                if (args.size >= 2) return false
                val targetPlayer = if (args.isEmpty()) sender else Bukkit.getPlayer(args[0])

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }
                targetPlayer.health = 0.0
                sender.sendMessage("${CC.YELLOW}Murdered!")
                plugin.logDebug("${sender.name} murdered ${targetPlayer.name}")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.isEmpty()) return false
        val targetPlayer = Bukkit.getPlayer(args[0])

        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }
        targetPlayer.health = 0.0
        sender.sendMessage("${CC.YELLOW}Murdered!")
        plugin.logDebug("Console murdered ${targetPlayer.name}")
        return true
    }
}
