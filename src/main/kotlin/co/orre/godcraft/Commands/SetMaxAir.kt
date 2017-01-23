package co.orre.godcraft.Commands

import co.orre.godcraft.God
import co.orre.godcraft.Util
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetMaxAir(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("GodCraft.SetMaxAir")) {
                var maxAir = 0
                var targetPlayer: Player? = null

                if (args.size > 2) {
                    return false
                } else {
                    if (args.isEmpty()) {
                        targetPlayer = sender
                        maxAir = 20
                    } else if (args.size == 1 && Util.isParsableToInt(args[0])) {
                        targetPlayer = sender
                        maxAir = Integer.parseInt(args[0])
                    } else if (args.size == 1 && !Util.isParsableToInt(args[0])) {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        maxAir = 20
                    } else if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        maxAir = Integer.parseInt(args[1])
                    }

                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                        return true
                    }

                    if (maxAir > plugin.config.getInt("maxair", 20)) {
                        sender.sendMessage(ChatColor.RED.toString() + "That is to much. Please use a number between 0 and " + plugin.config.getInt("maxair", 20))
                        return true
                    }
                }

                targetPlayer.maximumAir = maxAir
                sender.sendMessage(ChatColor.YELLOW.toString() + "Max Air Set!")
                return true
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You do not have permissions to do that!")
                return true
            }
        } else {
            var maxAir = 0
            var targetPlayer: Player? = null

            if (args.isEmpty() || args.size > 2) {
                return false
            } else {
                if (args.size == 1 && !Util.isParsableToInt(args[0])) {
                    targetPlayer = Bukkit.getPlayer(args[0])
                    maxAir = 20
                } else if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                    targetPlayer = Bukkit.getPlayer(args[0])
                    maxAir = Integer.parseInt(args[1])
                }

                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                    return true
                }
            }

            targetPlayer.maximumAir = maxAir
            sender.sendMessage(ChatColor.YELLOW.toString() + "Max Air Set!")
            return true
        }
    }
}
