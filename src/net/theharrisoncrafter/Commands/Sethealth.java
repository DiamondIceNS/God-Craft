package net.theharrisoncrafter.Commands;

import net.theharrisoncrafter.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sethealth implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Sethealth")){
				int health = 0;
				Player targetPlayer = null;
				
				if(args.length > 2){
					return false;
				}
				else{
					if (args.length == 1 && Util.isParsableToInt(args[0])){
						targetPlayer = (Player) sender;
						health = Integer.parseInt(args[0]);
					}
					else if (args.length == 1 && !Util.isParsableToInt(args[0])){
						targetPlayer = Bukkit.getPlayer(args[0]);
						health = 0;
					}
					else if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){
						targetPlayer = Bukkit.getPlayer(args[0]);
						health = Integer.parseInt(args[1]);
					}
					
					if(targetPlayer == null){
						sender.sendMessage(ChatColor.RED + "That player is not online!");
						return true;
					}
					
					if(health > targetPlayer.getMaxHealth()){
						sender.sendMessage(ChatColor.RED + "That is to much. Please use a number between 0 and " + targetPlayer.getMaxHealth());
						return true;
					}
				}
				
				targetPlayer.setHealth(health);
				sender.sendMessage(ChatColor.YELLOW + "Health Set!");
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			int health = 0;
			Player targetPlayer = null;
			
			if(args.length < 2 || args.length > 2){
				return false;
			}
			else{
				if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){
					targetPlayer = Bukkit.getPlayer(args[0]);
					health = Integer.parseInt(args[1]);
				}
				else{
					return false;
				}
				
				if(targetPlayer == null){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}
			}
			
			targetPlayer.setHealth(health);
			sender.sendMessage(ChatColor.YELLOW + "Health Set!");
			return true;
		}
	}
}
