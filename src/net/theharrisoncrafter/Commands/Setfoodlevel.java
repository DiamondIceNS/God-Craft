package net.theharrisoncrafter.Commands;

import net.theharrisoncrafter.God;
import net.theharrisoncrafter.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setfoodlevel implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Setfoodlevel")){
				int level = 0;
				Player targetPlayer = null;
				
				if(args.length > 2){
					return false;
				}
				else{
					if (args.length == 1 && Util.isParsableToInt(args[0])){
						targetPlayer = (Player) sender;
						level = Integer.parseInt(args[0]);
					}
					else if (args.length == 1 && !Util.isParsableToInt(args[0])){
						targetPlayer = Bukkit.getPlayer(args[0]);
						level = 0;
					}
					else if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){
						targetPlayer = Bukkit.getPlayer(args[0]);
						level = Integer.parseInt(args[1]);
					}
					
					if(targetPlayer == null){
						sender.sendMessage(ChatColor.RED + "That player is not online!");
						return true;
					}
					
					if(level > God.config.getInt("maxfoodlevel", 20)){
						sender.sendMessage(ChatColor.RED + "That is to much. Please use a number between 0 and " + God.config.getInt("maxfoodlevel", 20));
						return true;
					}
				}
				
				targetPlayer.setFoodLevel(level);
				sender.sendMessage(ChatColor.YELLOW + "Food Level Set!");
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			int level = 0;
			Player targetPlayer = null;
			
			if(args.length < 2 || args.length > 2){
				return false;
			}
			else{
				if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){
					targetPlayer = Bukkit.getPlayer(args[0]);
					level = Integer.parseInt(args[1]);
				}
				else{
					return false;
				}
				
				if(targetPlayer == null){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}
			}
			
			targetPlayer.setFoodLevel(level);
			sender.sendMessage(ChatColor.YELLOW + "Food Level Set!");
			return true;
		}
	}
}
