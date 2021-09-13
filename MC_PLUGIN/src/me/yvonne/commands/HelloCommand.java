package me.yvonne.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.yvonne.MainPlugin;

public class HelloCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private MainPlugin plugin;

	public HelloCommand(MainPlugin plugin) {
		this.plugin = plugin;
		plugin.getCommand("hello").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("only players may execute this command!");
			return true;
		}
		Player p = (Player) sender;
		
		if (p.hasPermission("hello.use")) {
			if(label.equalsIgnoreCase("hello")){
				p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Hello!");
				return true;
			}
		}
		else {
			p.sendMessage("You do not have permission to use this command!");
		}
		return false;
	}
	
}
