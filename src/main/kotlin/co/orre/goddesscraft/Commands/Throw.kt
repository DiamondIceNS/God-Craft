package co.orre.goddesscraft.Commands

import co.orre.goddesscraft.Goddess
import org.bukkit.Bukkit
import org.bukkit.ChatColor as CC
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.*

class Throw(val plugin: Goddess) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (sender.hasPermission("goddesscraft.throw")) {
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
                    "arrow" -> targetPlayer.launchProjectile(Arrow::class.java)
                    "dragonfireball", "dragon" -> targetPlayer.launchProjectile(DragonFireball::class.java)
                    "egg" -> targetPlayer.launchProjectile(Egg::class.java)
                    "enderpearl", "pearl" -> targetPlayer.launchProjectile(EnderPearl::class.java)
                    "fishhook" -> targetPlayer.launchProjectile(FishHook::class.java)
                    "largefireball" -> targetPlayer.launchProjectile(LargeFireball::class.java)
                    "llamaspit", "spit" -> targetPlayer.launchProjectile(LlamaSpit::class.java)
                    "shulkerbullet" -> targetPlayer.launchProjectile(ShulkerBullet::class.java)
                    "smallfireball", "blaze", "blazefireball" -> targetPlayer.launchProjectile(SmallFireball::class.java)
                    "snowball" -> targetPlayer.launchProjectile(Snowball::class.java)
                    "spectralarrow", "spectral" -> targetPlayer.launchProjectile(SpectralArrow::class.java)
                    "thrownexpbottle", "expbottle", "exp", "bottleo'enchanting" -> targetPlayer.launchProjectile(ThrownExpBottle::class.java)
                    "witherskull", "skull", "wither" -> targetPlayer.launchProjectile(WitherSkull::class.java)
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
            "arrow" -> targetPlayer.launchProjectile(Arrow::class.java)
            "dragonfireball", "dragon" -> targetPlayer.launchProjectile(DragonFireball::class.java)
            "egg" -> targetPlayer.launchProjectile(Egg::class.java)
            "enderpearl", "pearl" -> targetPlayer.launchProjectile(EnderPearl::class.java)
            "fishhook" -> targetPlayer.launchProjectile(FishHook::class.java) //TODO
            "largefireball", "ghast", "fireball", "ghastfireball" -> targetPlayer.launchProjectile(LargeFireball::class.java)
            "llamaspit", "spit" -> targetPlayer.launchProjectile(LlamaSpit::class.java) //TODO
            "shulkerbullet" -> targetPlayer.launchProjectile(ShulkerBullet::class.java) //TODO
            "smallfireball", "blaze", "blazefireball" -> targetPlayer.launchProjectile(SmallFireball::class.java)
            "snowball" -> targetPlayer.launchProjectile(Snowball::class.java)
            "spectralarrow", "spectral" -> targetPlayer.launchProjectile(SpectralArrow::class.java)
            "thrownexpbottle", "expbottle", "exp", "bottleo'enchanting" -> targetPlayer.launchProjectile(ThrownExpBottle::class.java)
            "witherskull", "skull", "wither" -> targetPlayer.launchProjectile(WitherSkull::class.java)
            else -> return false
        }
        plugin.logDebug("${sender.name} made ${targetPlayer.name} shoot a projectile (type: $item)")
        return true
    }
}
