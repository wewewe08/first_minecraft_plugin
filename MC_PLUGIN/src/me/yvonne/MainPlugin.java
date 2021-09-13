package me.yvonne;
import org.bukkit.plugin.java.JavaPlugin;

import me.yvonne.commands.CreeperRouletteCommand;
import me.yvonne.commands.FlingCommand;
import me.yvonne.commands.HelloCommand;
import me.yvonne.commands.eventHandlers;

public class MainPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
       
        new HelloCommand(this);
        new FlingCommand(this);
        new eventHandlers(this);
        new CreeperRouletteCommand(this);
        
        System.out.println("System is up and running!");
    }
}

