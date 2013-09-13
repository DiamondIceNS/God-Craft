package net.theharrisoncrafter.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Getenderchest implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(sender instanceof Player && sender.hasPermission("GodCraft.Getenderchest")){
			Player targetPlayer;
			String version = Bukkit.getBukkitVersion();
			
			if(version.startsWith("1")){
				version.replaceFirst("1.", "");
				version.substring(0, 1);
				if(Integer.parseInt(version.substring(0, 1)) < 3){
					sender.sendMessage("This version of minecraft is too old for that!");
					return true;
				}
			}
			
			if(args.length > 1){
				return false;
			}
			
			if(args.length < 1){
				targetPlayer = (Player) sender;
			}
			else{			
				targetPlayer = Bukkit.getPlayer(args[0]);
			}
			
			if(targetPlayer == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return true;
			}
			
			((Player) sender).openInventory(targetPlayer.getEnderChest());
			return true;
		}
		else{
			sender.sendMessage(ChatColor.RED + "You can not send that command!");
			return true;
		}
	}
}
