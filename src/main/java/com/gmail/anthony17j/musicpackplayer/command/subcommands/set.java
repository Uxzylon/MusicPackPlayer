package com.gmail.anthony17j.musicpackplayer.command.subcommands;

import com.gmail.anthony17j.musicpackplayer.MusicPackPlayer;
import com.gmail.anthony17j.musicpackplayer.command.subCommand;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class set extends subCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Modifier/Ajouter un son à une zone";
    }

    @Override
    public String getSyntax() {
        return "/musicpack set <region> <son>";
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        switch (args.length) {
            case 2:
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager wgRegions = container.get(BukkitAdapter.adapt(player.getWorld()));
                return new ArrayList(wgRegions.getRegions().keySet());
            case 3:
                if (MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region") != null) {
                    ArrayList<String> keyValues = new ArrayList<>();
                    for (String str : MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region").getKeys(false)) {
                        keyValues.add(MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region").getString(str));
                    }
                    return keyValues;
                } else {
                    return Collections.emptyList();
                }
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 2) {
            MusicPackPlayer.plugin.getConfig().set("Region." + args[1], args[2]);
            MusicPackPlayer.plugin.saveConfig();
            player.sendMessage(ChatColor.YELLOW + "Musique '" + args[2] + "' configur" + '\u00E9' + "e pour la r" + '\u00E9' + "gion " + args[1]);
        } else {
            player.sendMessage(ChatColor.RED + "Vous devez donner un nom de r" + '\u00E9' + "gion et un nom de musique!");
        }
    }
}
