package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetCrafting(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player && sender.hasPermission("goddesscraft.get_crafting")) {
            if (args.isNotEmpty()) return false

            if (sender.isOnline) {
                sender.openWorkbench(sender.location, true)
                plugin.logDebug("${sender.name} opened a Crafting Table window")
                return true
            }
            return false
        }
        sender.sendMessage("${CC.RED}You do not have permissions to do that!")
        return true
    }
}
