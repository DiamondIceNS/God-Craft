package net.theharrisoncrafter.Commands;

import net.theharrisoncrafter.God;
import net.theharrisoncrafter.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Boom implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if(sender instanceof Player){
			if(sender.hasPermission("GodCraft.Boom")){
				float explosionPower = 0;
				Player targetPlayer = null;
				
				if(args.length >= 3){
					return false;
				}
				else{
					if(args.length <= 0){											// /boom
						targetPlayer = (Player) sender;
						explosionPower = 0F;
					}
					else if (args.length == 1 && Util.isParsableToInt(args[0])){	// /boom 1
						targetPlayer = (Player) sender;
						explosionPower = Integer.parseInt(args[0]);
					}
					else if (args.length == 1 && !Util.isParsableToInt(args[0])){	// /boom foxboy992
						targetPlayer = Bukkit.getPlayer(args[0]);
						explosionPower = 0F;
					}
					else if(args.length == 2 && Util.isParsableToInt(args[0]) && !Util.isParsableToInt(args[1])){	// /boom 1 foxboy992
						targetPlayer = Bukkit.getPlayer(args[1]);
						explosionPower = Float.parseFloat(args[0]);
					}
					else if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){	// /boom foxboy992 1
						targetPlayer = Bukkit.getPlayer(args[0]);
						explosionPower = Float.parseFloat(args[1]);
					}
					
					if(targetPlayer == null){
						sender.sendMessage(ChatColor.RED + "That player is not online!");
						return true;
					}
					
					if(explosionPower > God.config.getInt("maxboompower", 10)){
						sender.sendMessage(ChatColor.RED + "That is to much power. Please use a number between 0 and " + God.config.getInt("maxboompower", 10));
						return true;
					}
				}
				
				targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), explosionPower);
				sender.sendMessage(ChatColor.YELLOW + "Boom!");
				return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to do that!");
				return true;
			}
		}
		else{
			float explosionPower = 0;
			Player targetPlayer = null;
			
			if(args.length > 2 || args.length < 1){
				return false;
			}
			else{
				if (args.length == 1 && !Util.isParsableToInt(args[0])){	// /boom foxboy992
					targetPlayer = Bukkit.getPlayer(args[0]);
					explosionPower = 0F;
				}
				else if(args.length == 2 && Util.isParsableToInt(args[0]) && !Util.isParsableToInt(args[1])){	// /boom 1 foxboy992
					targetPlayer = Bukkit.getPlayer(args[1]);
					explosionPower = Float.parseFloat(args[0]);
				}
				else if(args.length == 2 && !Util.isParsableToInt(args[0]) && Util.isParsableToInt(args[1])){	// /boom foxboy992 1
					targetPlayer = Bukkit.getPlayer(args[0]);
					explosionPower = Float.parseFloat(args[1]);
				}
				
				if(targetPlayer == null){
					sender.sendMessage(ChatColor.RED + "That player is not online!");
					return true;
				}
			}
			
			targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), explosionPower);
			sender.sendMessage(ChatColor.YELLOW + "Boom!");
			return true;
		}
	}
}
