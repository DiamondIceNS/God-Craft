package net.theharrisoncrafter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Logger;
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
	private File configFile;
	public static FileConfiguration config;
	
	@Override
	public void onLoad() {
		log = this.getLogger();
		pdf = this.getDescription();
		
		firstRun();
		config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	@Override
	public void onEnable(){
		log.info("Enableling");
	}
	
	@Override
	public void onDisable(){
		log.info("Disableing");
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
	
	private void firstRun(){
		if(!this.configFile.exists()){
			this.configFile.getParentFile().mkdirs();
	    	this.copy(this.getResource("config.yml"), this.configFile);
		}
	}
	
	private void copy(InputStream in, File file){
		try{
			FileOutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			
			out.close();
			in.close();
		}
		catch(Exception e){}
	}
}