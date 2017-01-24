package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Troll(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.troll")) {
                val targetPlayer: Player?

                when {
                    args.size > 1 -> return false
                    args.isEmpty() -> targetPlayer = sender
                    args.size == 1 -> targetPlayer = Bukkit.getPlayer(args[0])
                    else -> targetPlayer = null
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                targetPlayer.world.createExplosion(targetPlayer.location, 0f)
                targetPlayer.world.strikeLightningEffect(targetPlayer.location)
                sender.sendMessage("${CC.YELLOW}u mad?")
                plugin.logDebug("${sender.name} trolled ${targetPlayer.name}")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.size != 1) return false
        val targetPlayer: Player? = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        targetPlayer.world.createExplosion(targetPlayer.location, 0f)
        targetPlayer.world.strikeLightningEffect(targetPlayer.location)
        sender.sendMessage("${CC.YELLOW}u mad?")
        plugin.logDebug("Console trolled ${targetPlayer.name}")
        return true
    }
}
