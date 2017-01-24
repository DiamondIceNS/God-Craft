package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetEnchanting(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player && sender.hasPermission("godcraft.get_enchanting")) {
            if (args.isNotEmpty()) return false

            if (sender.isOnline) {
                sender.openEnchanting(sender.location, true)
                plugin.logDebug("${sender.name} opened an Enchanting Table window")
                return true
            }
            return false
        }

        sender.sendMessage("${CC.RED}You do not have permissions to do that!")
        return true

    }
}
