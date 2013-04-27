package net.theharrisoncrafter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import net.theharrisoncrafter.Commands.Nickname;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class God extends JavaPlugin{
	Logger log;
	public static PluginDescriptionFile pdf;
	public static FileConfiguration config;
	
	@Override
	public void onLoad() {
		log = this.getLogger();
		pdf = this.getDescription();
		
		this.saveDefaultConfig();
		config = this.getConfig();
	}
	
	@Override
	public void onEnable(){
	}
	
	@Override
	public void onDisable(){
	}
	
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args){
		try{
			String c = command.getName();
			return ((CommandExecutor) Class.forName("net.theharrisoncrafter.Commands." + c.toUpperCase().charAt(0) + c.toLowerCase().substring(1)).getConstructor().newInstance()).onCommand(sender, command, label, args);
		}catch(final Exception e){
			sender.sendMessage("Something went wrong (" + e.getClass() + "): " + e.getMessage() + "!(" + e.getCause() + ")");
			sender.sendMessage("Please report this problem to theharrisoncrafter@gmail.com with this error and a list of the steps you took to get this error.");
			e.printStackTrace();
		}
		return false;
	}
	
	public String getVertsion(){
		return pdf.getVersion();
	}
}