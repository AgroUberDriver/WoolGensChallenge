package me.agro.chatfilter;

import me.agro.chatfilter.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;


    public void onEnable() {
        setInstance(this);

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        System.out.println("Chat Filter Enabled!");

    }

    public static Main getInstance() {
        return instance;
    }

    public void onDisable(){

    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }
}
