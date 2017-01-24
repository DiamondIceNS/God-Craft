package co.orre.godcraft.Commands

import co.orre.godcraft.God
import co.orre.godcraft.Util
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetLevel(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.set_level")) {
                var level = 0
                val targetPlayer: Player?

                if (args.size > 2) return false
                when {
                    args.size == 1 && !Util.isParsableToInt(args[0]) -> targetPlayer = Bukkit.getPlayer(args[0])
                    args.size == 1 && Util.isParsableToInt(args[0]) -> {
                        targetPlayer = sender
                        level = args[0].toInt()
                    }
                    args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1]) -> {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        level = args[1].toInt()
                    }
                    else -> return false
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                if (level > plugin.config.getInt("max_level", 30)) {
                    sender.sendMessage("${CC.RED}That is to much. Please use a number between 0 and " +
                            "${plugin.config.getInt("max_level", 30)}")
                    return true
                }

                targetPlayer.level = level
                sender.sendMessage("${CC.YELLOW}Level set!")
                plugin.logDebug("${sender.name} set ${targetPlayer.name}'s level to $level")
                return true
            } else {
                sender.sendMessage("${CC.RED}You do not have permissions to do that!")
                return true
            }
        } else {
            val level: Int
            val targetPlayer: Player?

            if (args.size != 2) return false
            else {
                if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                    targetPlayer = Bukkit.getPlayer(args[0])
                    level = args[1].toInt()
                } else return false

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }
            }

            targetPlayer.level = level
            sender.sendMessage("${CC.YELLOW}Level set!")
            plugin.logDebug("Console set ${targetPlayer.name}'s level to $level")
            return true
        }
    }
}
