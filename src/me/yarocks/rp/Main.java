package me.yarocks.rp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Created by User Name on 9/16/2014.
 */
public class Main extends JavaPlugin {


	public void onEnable(){
		getConfig().addDefault("time", 1);
		getConfig().addDefault("message", "&6Happy Halloween!");
		getConfig().options().copyDefaults(true);
		saveConfig();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if (Bukkit.getOnlinePlayers().length > 0) {
					Player p = Bukkit.getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];
					if (!p.hasPermission("player.exempt")) {
						if (p.getInventory().firstEmpty() != -1) {
							if (p.getInventory().getHelmet() != null) {
								final ItemStack helmet = p.getInventory().getHelmet();

								p.getInventory().addItem(helmet);
								p.getInventory().setHelmet(new ItemStack(Material.PUMPKIN, 1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
							}else {
								p.getInventory().setHelmet(new ItemStack(Material.PUMPKIN, 1));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
							}
						}
					}
				}
			}
		}, getConfig().getInt("time") * 60, getConfig().getInt("time") * 20 );
	        PluginManager pm = Bukkit.getServer().getPluginManager();
	    printEnable();

    }

	public void onDisable(){
		printDisable();
	}
	public void printEnable(){
		ConsoleCommandSender css = Bukkit.getConsoleSender();
		css.sendMessage("§a" + getDescription().getFullName() + " Has Been Enabled!");
	}

	public void printDisable(){
		ConsoleCommandSender css = Bukkit.getConsoleSender();
		css.sendMessage("§c" + getDescription().getFullName() + " Has Been Disabled!");
	}
}
