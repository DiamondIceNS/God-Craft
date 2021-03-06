package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Ping(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.ping")) {
                sender.sendMessage("${CC.GREEN}Pong!")
                plugin.logDebug("Received a ping from ${sender.name}")
                return true
            } else {
                sender.sendMessage("${CC.RED}You do not have permissions to do that!")
                return true
            }
        } else {
            sender.sendMessage("${CC.YELLOW}Why would you do this?")
            plugin.logDebug("Console is being a smartass")
            return true
        }
    }
}
