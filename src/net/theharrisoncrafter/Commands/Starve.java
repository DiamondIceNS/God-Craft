package net.theharrisoncrafter.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Starve implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Starve")){
				Player targetPlayer;
				
				if(args.length > 1){
					return false;
				}
				else{
					if(args.length < 1){
						targetPlayer = (Player) sender;
					}
					else{
						targetPlayer = sender.getServer().getPlayer(args[0]);
					}
				}
				
				if(targetPlayer == null){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}		
				
				targetPlayer.setFoodLevel(0);
				sender.sendMessage(ChatColor.YELLOW + "Starved!");
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			Player targetPlayer;
			if(args.length < 1 || args.length > 1 ){
				return false;
			}
			else{
				targetPlayer = Bukkit.getPlayer(args[0]);
			}
			
			if(targetPlayer == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return true;
			}		
			
			targetPlayer.setFoodLevel(0);
			sender.sendMessage(ChatColor.YELLOW + "Burn!");
			return true;
		}
	}

}