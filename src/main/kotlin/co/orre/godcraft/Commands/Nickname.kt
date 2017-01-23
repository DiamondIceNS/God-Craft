package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Nickname(val plugin: God) : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player) {
            if (sender.hasPermission("GodCraft.Nickname")) {
                val targetPlayer: Player?
                val newName: String

                if (args.size >= 3) {
                    return false
                } else if (args.isEmpty()) {
                    targetPlayer = sender
                    newName = sender.getName().toString()
                } else if (args.size == 1) {
                    targetPlayer = sender
                    newName = args[0]
                } else {
                    targetPlayer = sender.getServer().getPlayer(args[0])
                    newName = args[1]
                }

                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                    return true
                }
                if (targetPlayer !is Player) {
                    sender.sendMessage(ChatColor.RED.toString() + "This command must be used on a player!")
                    return true
                }

                setNickName(targetPlayer, newName)
                return true
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You do not have permissions to do that!")
                return true
            }
        } else {
            val targetPlayer: Player?
            val newName: String

            if (args.isEmpty() || args.size > 2) {
                return false
            }

            targetPlayer = Bukkit.getPlayer(args[0])

            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                return true
            }
            if (targetPlayer !is Player) {
                sender.sendMessage(ChatColor.RED.toString() + "This command must be used on a player!")
                return true
            }

            if (args.size == 1) {
                newName = Bukkit.getPlayer(args[0]).name
            } else {
                newName = args[1]
            }

            setNickName(targetPlayer, newName)
            return true
        }
    }

    companion object {

        fun setNickName(player: Player, name: String) {
            player.playerListName = name
            player.displayName = name
        }
    }
}