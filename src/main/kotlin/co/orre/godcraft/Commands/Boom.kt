package co.orre.godcraft.Commands

import co.orre.godcraft.God
import co.orre.godcraft.Util
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.ChatColor as CC

class Boom (val plugin: God) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("godcraft.boom")) {
                var explosionPower = 0f
                var targetPlayer: Player? = null

                if (args.size >= 3) return false
                when {
                    args.isEmpty() -> targetPlayer = sender
                    args.size == 1 && !Util.isParsableToInt(args[0]) -> targetPlayer = Bukkit.getPlayer(args[0])
                    args.size == 1 && Util.isParsableToInt(args[0]) -> {
                        targetPlayer = sender
                        explosionPower = args[0].toFloat()

                    }
                    args.size == 2 && Util.isParsableToInt(args[0]) -> {
                        targetPlayer = Bukkit.getPlayer(args[1])
                        explosionPower = args[0].toFloat()

                    }
                    args.size == 2 && Util.isParsableToInt(args[1]) -> {
                        targetPlayer = Bukkit.getPlayer(args[0])
                        explosionPower = args[1].toFloat()
                    }
                }

                if (targetPlayer == null) {
                    sender.sendMessage("${CC.RED}That player could not be found!")
                    return true
                }

                if (explosionPower > plugin.configuration.MAX_BOOM_POWER) {
                    sender.sendMessage("${CC.RED}That is to much power. Please use a number " +
                            "between 0 and ${plugin.configuration.MAX_BOOM_POWER}")
                    return true
                }

                targetPlayer.world.createExplosion(targetPlayer.location, explosionPower)
                sender.sendMessage("${CC.YELLOW}Boom!")
                plugin.logDebug("${sender.name} blew up ${targetPlayer.name} (power: $explosionPower)")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }

        var explosionPower = 0f
        var targetPlayer: Player? = null

        if (args.size > 2 || args.isEmpty()) return false
        when {
            args.size == 1 && !Util.isParsableToInt(args[0]) -> targetPlayer = Bukkit.getPlayer(args[0])
            args.size == 2 && Util.isParsableToInt(args[0]) -> {
                targetPlayer = Bukkit.getPlayer(args[1])
                explosionPower = args[0].toFloat()
            }
            args.size == 2 && Util.isParsableToInt(args[1]) -> {
                targetPlayer = Bukkit.getPlayer(args[0])
                explosionPower = args[1].toFloat()
            }
        }

        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        targetPlayer.world.createExplosion(targetPlayer.location, explosionPower)
        sender.sendMessage("${CC.YELLOW}Boom!")
        plugin.logDebug("Console blew up ${targetPlayer.name} (power: $explosionPower)")
        return true
    }
}
