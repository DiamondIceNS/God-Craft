package co.orre.godcraft.Commands

import co.orre.godcraft.God
import co.orre.godcraft.Util
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetMaxAir(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("GodCraft.SetMaxAir")) {
                var maxAir = 20
                val targetPlayer: Player?

                if (args.size > 2) return false
                when {
                    args.isEmpty() -> targetPlayer = sender
                    args.size == 1 && !Util.isParsableToInt(args[0]) -> targetPlayer = Bukkit.getPlayer(args[0])
                    args.size == 1 && Util.isParsableToInt(args[0]) -> {
                        targetPlayer = sender
                        maxAir = args[0].toInt()
                    }
                    args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1]) -> {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        maxAir = args[1].toInt()
                    }
                    else -> return false
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                if (maxAir > plugin.config.getInt("max_air", 20)) {
                    sender.sendMessage("${CC.RED}That is to much. Please use a number between 0 and " +
                            "${plugin.config.getInt("max_air", 20)}")
                    return true
                }

                targetPlayer.maximumAir = maxAir
                sender.sendMessage("${CC.YELLOW}Max air set!")
                plugin.logDebug("${sender.name} set ${targetPlayer.name}'s maximum air to $maxAir")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        var maxAir = 20
        val targetPlayer: Player?

        when {
            args.isEmpty() || args.size > 2 -> return false
            args.size == 1 && !Util.isParsableToInt(args[0]) -> targetPlayer = Bukkit.getPlayer(args[0])
            args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1]) -> {
                targetPlayer = Bukkit.getPlayer(args[0])
                maxAir = args[1].toInt()
            }
            else -> return false
        }

        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        targetPlayer.maximumAir = maxAir
        sender.sendMessage("${CC.YELLOW}Max air set!")
        plugin.logDebug("Console set ${targetPlayer.name}'s maximum air to $maxAir")
        return true
    }
}
