package net.theharrisoncrafter.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Throw implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Throw")){
				Player targetPlayer;
				String item;
				
				if(args.length < 1 || args.length > 2){
					return false;
				}
				
				if(args.length > 1){
					targetPlayer = sender.getServer().getPlayer(args[0]);
					item = args[1];
				}
				else{
					targetPlayer = (Player) sender;
					item = args[0];
				}
				
				if(targetPlayer == null){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}
				
				if(item.toLowerCase().equals("egg")){
					targetPlayer.throwEgg();
				}
				else if(item.toLowerCase().equals("snowball")){
					targetPlayer.throwSnowball();
				}
				else if(item.toLowerCase().equals("arrow")){
					targetPlayer.shootArrow();
				}
				
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			Player targetPlayer;
			
			if(args.length < 2 || args.length > 2){
				return false;
			}
			
			targetPlayer = Bukkit.getPlayer(args[0]);
			
			if(targetPlayer == null){
				sender.sendMessage(ChatColor.RED + "That player is not online!");
				return true;
			}
			
			if(args[1].toLowerCase().equals("egg")){
				targetPlayer.throwEgg();
			}
			else if(args[1].toLowerCase().equals("snowball")){
				targetPlayer.throwSnowball();
			}
			else if(args[1].toLowerCase().equals("arrow")){
				targetPlayer.shootArrow();
			}
			
			return true;
		}
	}
}
