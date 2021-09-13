package me.yvonne.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.yvonne.MainPlugin;

public class eventHandlers implements Listener {

    private MainPlugin plugin;
    
    //constructor
    public eventHandlers(MainPlugin plugin){
        this.plugin = plugin;
        
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //CREEPER ROULETTE
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event){
        Player player = event.getPlayer(); //player who broke the block
        Location loc = player.getLocation();
        World wor = player.getWorld();

        String numC = plugin.getConfig().getString("num-creepers");
        int numCreepers = Integer.parseInt(numC);

        Random rnd = new Random(); 
        String creeperSpawn = plugin.getConfig().getString("creeper-spawnrates");
        int creeperSR = Integer.parseInt(creeperSpawn);
        int creeperSpawnRate = rnd.nextInt(creeperSR); //500
        
        if (plugin.getConfig().getBoolean("creeper-toggle")){ //if the CR is toggled	
            if(creeperSpawnRate < 5){
                player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "[CREEPER ROULETTE] better luck next time...");
            }
            player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "[CREEPER ROULETTE] you just rolled a " + creeperSpawnRate + "/500!");
        }

        //5% chance of spawning
        if (creeperSpawnRate < 5) {
            for (int n = 0; n < numCreepers; n++){
                wor.spawnEntity(loc, EntityType.CREEPER);
            }
        }
    }

    //ACID RAIN
    BukkitRunnable task;

    private void checkPlayerPos(){
        String acidCfg = plugin.getConfig().getString("acid-dmg");
        System.out.println(acidCfg);
        int acidDMG = Integer.parseInt(acidCfg);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getLocation().getWorld().isThundering() || player.getLocation().getWorld().hasStorm()) {
                int blockLocation = player.getLocation().getWorld().getHighestBlockYAt(player.getLocation()); //gets location of the highest block the player is on
                if (blockLocation <= player.getLocation().getY()) {
                    player.damage(acidDMG);
                }
                else{
                    //if player is standing underneath a block
                    System.out.println("player is under a block");
                }
            }
        }
    }

    @EventHandler
    public void onAcidRain(WeatherChangeEvent event) { 
    	System.out.println("test");
        if (event.toWeatherState()) { //if it starts to rain
            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "ACID IS RAINING FROM THE SKY!");
            task = new BukkitRunnable() {
            	@Override
                public void run(){
                    checkPlayerPos(); //constantly check for players position
                }
            };
            task.runTaskTimer(plugin, 20, 20);
        }
        else if (!event.toWeatherState()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "ACID HAS STOPPED RAINING FROM THE SKY!");
            task.cancel();
        }
        else System.out.println("debug line");
    }
}
