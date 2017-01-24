package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import co.orre.goddesscraft.Util
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetFoodLevel(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.set_food_level")) {
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

                if (level > plugin.config.getInt("max_food_level", 20)) {
                    sender.sendMessage("${CC.RED}That is to much. Please use a number between 0 and " +
                            "${plugin.config.getInt("max_food_level", 20)}")
                    return true
                }

                targetPlayer.foodLevel = level
                sender.sendMessage("${CC.YELLOW}Food level set!")
                plugin.logDebug("${sender.name} set ${targetPlayer.name}'s food level to $level")
                return true
            }
            sender.sendMessage("${CC.RED}You do not have permissions to do that!")
            return true
        }
        val level: Int
        val targetPlayer: Player?

        if (args.size == 2) return false
        if (args.size != 2 || Util.isParsableToInt(args[0]) || !Util.isParsableToInt(args[1])) return false
        targetPlayer = Bukkit.getPlayer(args[0])
        level = args[1].toInt()

        if (targetPlayer == null) {
            sender.sendMessage("${CC.RED}That player could not be found!")
            return true
        }

        targetPlayer.foodLevel = level
        sender.sendMessage("${CC.YELLOW}Food level set!")
        plugin.logDebug("Console set ${targetPlayer.name}'s food level to $level")
        return true
    }
}
