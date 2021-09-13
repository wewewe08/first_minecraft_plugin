package me.yvonne.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.yvonne.MainPlugin;

import java.util.ArrayList;

public class FlingCommand implements CommandExecutor{
    @SuppressWarnings("unused")
    private MainPlugin plugin;

    public FlingCommand(MainPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("fling").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("fling")){
            ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());

            if (sender instanceof Player) {
                Player cmdSender =  (Player) sender;

                if(cmdSender.hasPermission("fling.use")){
                    Player player = Bukkit.getPlayer(args[0]);
                    for(Player p : players){
                        if (player.equals(p)){
                            cmdSender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "You just sent " + p.getName() + " flying!");
                            player.setVelocity(player.getLocation().getDirection().multiply(50).setY(50));
                            return true;
                        }
                    }
                }
                cmdSender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You do not have permission to use this command!");
            }
            else{
                sender.sendMessage("cannot use this command in the console.");
                return true;
            }
        }
        return false;
    }
}