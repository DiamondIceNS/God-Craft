package net.theharrisoncrafter.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lightning implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender instanceof Player&&sender.hasPermission("GodCraft.Lightning")){
			if (args.length >= 2){
				return false;
			}
			
			if(args.length == 1){
				if(args[0].equals("1")){
					((Player) sender).getWorld().strikeLightning(((Player) sender).getTargetBlock(null, 10000).getLocation());
					sender.sendMessage(ChatColor.YELLOW + "Kaboom!");
					return true;
				}
				else if (args[0].equals("0")){
					((Player) sender).getWorld().strikeLightningEffect(((Player) sender).getTargetBlock(null, 10000).getLocation());
					sender.sendMessage(ChatColor.YELLOW + "Kaboom!");
					return true;
				}
			}
			else{
				((Player) sender).getWorld().strikeLightningEffect(((Player) sender).getTargetBlock(null, 10000).getLocation());
				sender.sendMessage(ChatColor.YELLOW + "Kaboom!");
				return true;
			}
		}
		else{
			sender.sendMessage(ChatColor.RED + "You can not send that command!");
			return true;
		}
		return false;
	}
}