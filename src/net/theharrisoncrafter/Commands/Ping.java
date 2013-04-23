package net.theharrisoncrafter.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Ping")){
				sender.sendMessage(ChatColor.GREEN + "Pong");
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			sender.sendMessage(ChatColor.YELLOW + "Why would you do this?");
			return true;
		}
	}
}
