package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.ChatColor as CC
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Lightning(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player && sender.hasPermission("goddesscraft.lightning")) {
            if (args.size > 1) return false

            val set: Set<Material>? = null
            if (args.size == 1) {
                when (args[0]) {
                    "1" -> {
                        sender.world.strikeLightning(sender.getTargetBlock(set, 10000).location)
                        sender.sendMessage("${CC.YELLOW}Kaboom!")
                        plugin.logDebug("${sender.name} cast down a bolt of hard lightning")
                        return true
                    }
                    "0" -> {
                        sender.world.strikeLightningEffect(sender.getTargetBlock(set, 10000).location)
                        sender.sendMessage("${CC.YELLOW}Kaboom!")
                        plugin.logDebug("${sender.name} cast down a bolt of soft lightning")
                        return true
                    }
                    else -> return false
                }
            }
            sender.world.strikeLightningEffect(sender.getTargetBlock(set, 10000).location)
            sender.sendMessage("${CC.YELLOW}Kaboom!")
            plugin.logDebug("${sender.name} cast down a bolt of soft lightning")
            return true
        }
        sender.sendMessage("${CC.RED}You do not have permissions to do that!")
        return true
    }
}