package net.theharrisoncrafter.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Getinventory implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(sender instanceof Player && sender.hasPermission("GodCraft.Getinventory")){
			if(args.length < 1 || args.length > 1){
				return false;
			}
			
			Player targetPlayer = sender.getServer().getPlayer(args[0]);
			if(targetPlayer.isOnline()){
				((Player) sender).openInventory(targetPlayer.getInventory());
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "That plyer is not online");
				return true;
			}
		}
		else{
			sender.sendMessage(ChatColor.RED + "You can not send that command!");
			return true;
		}
	}
}
