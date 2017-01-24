package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Heal(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.heal")) {
                if (args.size > 1) return false
                val targetPlayer = if (args.isEmpty()) sender else Bukkit.getPlayer(args[0])

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }
                targetPlayer.health = 20.0
                sender.sendMessage("${CC.YELLOW}Healed!")
                plugin.logDebug("${sender.name} healed ${targetPlayer.name}")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.size != 1) return false
        val targetPlayer = Bukkit.getPlayer(args[0])

        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }
        targetPlayer.health = 20.0
        sender.sendMessage("${CC.YELLOW}Healed!")
        plugin.logDebug("Console healed ${targetPlayer.name}")
        return true
    }
}
