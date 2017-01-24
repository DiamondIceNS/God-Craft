package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Arrow
import org.bukkit.entity.Egg
import org.bukkit.entity.Player
import org.bukkit.entity.Snowball

class Throw(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.throw")) {
                val targetPlayer: Player?
                val item: String

                if (args.isEmpty() || args.size > 2) return false

                if (args.size == 1) {
                    targetPlayer = sender
                    item = args[0].toLowerCase()
                } else {
                    targetPlayer = sender.getServer().getPlayer(args[0])
                    item = args[1].toLowerCase()
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                when (item) {
                    "egg" -> targetPlayer.launchProjectile(Egg::class.java)
                    "snowball" -> targetPlayer.launchProjectile(Snowball::class.java)
                    "arrow" -> targetPlayer.launchProjectile(Arrow::class.java)
                    else -> return false
                }
                plugin.logDebug("${sender.name} made ${targetPlayer.name} shoot a projectile (type: $item)")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.size != 2) return false
        val targetPlayer: Player? = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        val item = args[1].toLowerCase()
        when (item) {
            "egg" -> targetPlayer.launchProjectile(Egg::class.java)
            "snowball" -> targetPlayer.launchProjectile(Snowball::class.java)
            "arrow" -> targetPlayer.launchProjectile(Arrow::class.java)
            else -> return false
        }
        plugin.logDebug("${sender.name} made ${targetPlayer.name} shoot a projectile (type: $item)")
        return true
    }
}
