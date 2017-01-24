package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Troll(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.troll")) {
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
        return true
    }
}
