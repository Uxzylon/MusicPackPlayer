package com.gmail.anthony17j.musicpackplayer;

import com.gmail.anthony17j.musicpackplayer.command.musicpackCommand;
import com.gmail.anthony17j.musicpackplayer.event.playerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MusicPackPlayer extends JavaPlugin {

    public static MusicPackPlayer plugin;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.getLogger().info("Started!");

        plugin.getConfig().options().copyDefaults(true);
        plugin.getConfig().options().header("Fichier de config pour MusicPackPlayer");
        plugin.saveConfig();

        getCommand("musicpack").setExecutor(new musicpackCommand());
        getServer().getPluginManager().registerEvents(new playerJoinEvent(), this);
    }

    @Override
    public void onDisable() {
        plugin.getLogger().info("Stopped!");
    }
}