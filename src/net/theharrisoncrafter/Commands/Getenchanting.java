package net.theharrisoncrafter.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Getenchanting implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(sender instanceof Player && sender.hasPermission("GodCraft.Getenchanging")){
			if(args.length < 0 || args.length > 0){
				return false;
			}
			
			if(((Player) sender).isOnline()){
				((Player) sender).openEnchanting(((Player) sender).getLocation(), true);
				return true;
			}
			return false;
		}
		else{
			sender.sendMessage(ChatColor.RED + "You can not send that command!");
			return true;
		}
	}
}
