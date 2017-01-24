package co.orre.godcraft.Commands

import co.orre.godcraft.God
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Nickname(val plugin: God) : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {

        if (sender is Player) {
            if (sender.hasPermission("godcraft.nickname")) {
                val targetPlayer: Player?
                val newName: String

                when {
                    args.size > 2 -> return false
                    args.isEmpty() -> {
                        targetPlayer = sender
                        newName = sender.name
                    }
                    args.size == 1 -> {
                        targetPlayer = sender
                        newName = args[0]
                    }
                    else -> {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        newName = args[1]
                    }
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                setNickName(targetPlayer, newName)
                plugin.logDebug("${sender.name} changed ${targetPlayer.name}'s nickname to $newName")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        if (args.isEmpty() || args.size > 2) return false

        val targetPlayer: Player? = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        val newName = if (args.size == 1) Bukkit.getPlayer(args[0]).name else args[1]

        setNickName(targetPlayer, newName)
        plugin.logDebug("Console changed ${targetPlayer.name}'s name to $newName")
        return true
    }

    companion object {
        fun setNickName(player: Player, name: String) {
            player.playerListName = name
            player.displayName = name
        }
    }
}