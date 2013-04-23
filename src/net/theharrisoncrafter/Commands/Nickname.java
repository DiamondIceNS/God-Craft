package net.theharrisoncrafter.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nickname implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Nickname")){
				Player targetPlayer;
				String newName;
			
				if(args.length >=3){
					return false;
				}
				else if (args.length == 0){
					targetPlayer = (Player) sender;
					newName = sender.getName().toString();
				}
				else if(args.length == 1){
					targetPlayer = (Player) sender;
					newName = args[0];
				}
				else{
					targetPlayer = sender.getServer().getPlayer(args[0]);
					newName = args[1];
					if (!(targetPlayer instanceof Player)){
						sender.sendMessage(ChatColor.RED + "This command must be used on a player by a player!");
						return true;
					}
					if(!(targetPlayer.isOnline())){
						sender.sendMessage(ChatColor.RED + "That player is not online!");
						return true;
					}
				}
				
				setNickName(targetPlayer, newName);
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			Player targetPlayer;
			String newName;
		
			if(args.length < 1 || args.length > 2){
				return false;
			}
			
			if(args.length == 1){
				targetPlayer = Bukkit.getPlayer(args[0]);
				newName = Bukkit.getPlayer(args[0]).getName();
			}
			else{
				targetPlayer = Bukkit.getPlayer(args[0]);
				newName = args[1];
				
				if (!(targetPlayer instanceof Player)){
					sender.sendMessage(ChatColor.RED + "This command must be used on a player!");
					return true;
				}
				if(!(targetPlayer.isOnline())){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}
			}
			
			setNickName(targetPlayer, newName);
			return true;
		}
	}
	
	public void setNickName(Player player, String name){
		player.setPlayerListName(name);
		player.setDisplayName(name);
	}
}