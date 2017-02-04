package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Feed(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.feed")) {
                if (args.size > 1) return false
                val targetPlayer = if (args.isEmpty()) sender else Bukkit.getPlayer(args[0])

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                targetPlayer.foodLevel = 20
                targetPlayer.saturation = 20f
                sender.sendMessage("${CC.YELLOW}Yum!")
                plugin.logDebug("${sender.name} fed ${targetPlayer.name}")
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

        targetPlayer.foodLevel = 20
        targetPlayer.saturation = 20f
        sender.sendMessage("${CC.YELLOW}Yum!")
        plugin.logDebug("Console fed ${targetPlayer.name}")
        return true
    }

}