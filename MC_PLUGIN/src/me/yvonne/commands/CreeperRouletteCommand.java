package me.yvonne.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.yvonne.MainPlugin;

public class CreeperRouletteCommand implements CommandExecutor{
    private MainPlugin plugin;
    
    public CreeperRouletteCommand(MainPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("creeperroulette").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player cmdSender = (Player) sender;
        boolean crEnabled = plugin.getConfig().getBoolean("creeper-toggle");
        if(label.equalsIgnoreCase("creeperroulette") && !crEnabled){ 
            plugin.getConfig().set("creeper-toggle", true);
            cmdSender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "[CREEPER ROULETTE] SHOW CREEPER RATES: " + ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED");
        }
        else if(label.equalsIgnoreCase("creeperroulette") && crEnabled){
        	plugin.getConfig().set("creeper-toggle", false);
            cmdSender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "[CREEPER ROULETTE] SHOW CREEPER RATES: " + ChatColor.RED + "" + ChatColor.BOLD + "DISABLED");
        }
        else{
            sender.sendMessage("cannot use this command in the console.");
            return true;
        }
        return false;
    }
}