package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor
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
            if (sender.hasPermission("GodCraft.Throw")) {
                val targetPlayer: Player?
                val item: String

                if (args.isEmpty() || args.size > 2) {
                    return false
                }

                if (args.size > 1) {
                    targetPlayer = sender.getServer().getPlayer(args[0])
                    item = args[1]
                } else {
                    targetPlayer = sender
                    item = args[0]
                }

                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                    return true
                }

                if (item.toLowerCase() == "egg") {
                    targetPlayer.launchProjectile(Egg::class.java)
                } else if (item.toLowerCase() == "snowball") {
                    targetPlayer.launchProjectile(Snowball::class.java)
                } else if (item.toLowerCase() == "arrow") {
                    targetPlayer.launchProjectile(Arrow::class.java)
                }

                return true
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You do not have permissions to do that!")
                return true
            }
        } else {
            val targetPlayer: Player?

            if (args.size < 2 || args.size > 2) {
                return false
            }

            targetPlayer = Bukkit.getPlayer(args[0])

            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                return true
            }

            if (args[1].toLowerCase() == "egg") {
                targetPlayer.launchProjectile(Egg::class.java)
            } else if (args[1].toLowerCase() == "snowball") {
                targetPlayer.launchProjectile(Snowball::class.java)
            } else if (args[1].toLowerCase() == "arrow") {
                targetPlayer.launchProjectile(Arrow::class.java)
            }

            return true
        }
    }
}
