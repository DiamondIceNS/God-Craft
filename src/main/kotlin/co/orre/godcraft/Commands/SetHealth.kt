package co.orre.godcraft.Commands

import co.orre.godcraft.God
import co.orre.godcraft.Util
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetHealth(val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("GodCraft.SetHealth")) {
                var health = 0
                var targetPlayer: Player? = null

                if (args.size > 2) {
                    return false
                } else {
                    if (args.size == 1 && Util.isParsableToInt(args[0])) {
                        targetPlayer = sender
                        health = Integer.parseInt(args[0])
                    } else if (args.size == 1 && !Util.isParsableToInt(args[0])) {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        health = 0
                    } else if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        health = Integer.parseInt(args[1])
                    }

                    if (targetPlayer == null) {
                        sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                        return true
                    }

                    if (health > targetPlayer.maxHealth) {
                        sender.sendMessage(ChatColor.RED.toString() + "That is to much. Please use a number between 0 and " + targetPlayer.maxHealth)
                        return true
                    }
                }

                targetPlayer.health = health.toDouble()
                sender.sendMessage(ChatColor.YELLOW.toString() + "Health Set!")
                return true
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You do not have permissions to do that!")
                return true
            }
        } else {
            var health = 0
            var targetPlayer: Player? = null

            if (args.size < 2 || args.size > 2) {
                return false
            } else {
                if (args.size == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])) {
                    targetPlayer = Bukkit.getPlayer(args[0])
                    health = Integer.parseInt(args[1])
                } else {
                    return false
                }

                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "That player is not online!")
                    return true
                }
            }

            targetPlayer.health = health.toDouble()
            sender.sendMessage(ChatColor.YELLOW.toString() + "Health Set!")
            return true
        }
    }
}
