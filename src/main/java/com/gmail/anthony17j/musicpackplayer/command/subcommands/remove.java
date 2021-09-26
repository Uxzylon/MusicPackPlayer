package com.gmail.anthony17j.musicpackplayer.command.subcommands;

import com.gmail.anthony17j.musicpackplayer.MusicPackPlayer;
import com.gmail.anthony17j.musicpackplayer.command.subCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class remove extends subCommand {

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Supprime le son d'une région";
    }

    @Override
    public String getSyntax() {
        return "/musicpack remove <region>";
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        if (args.length == 2) {
            if (MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region") != null) {
                Set<String> regions = MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region").getKeys(false);
                return new ArrayList<>(regions);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1) {
            if (MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region") != null) {
                Set<String> regions = MusicPackPlayer.plugin.getConfig().getConfigurationSection("Region").getKeys(false);
                if (regions.contains(args[1])) {
                    MusicPackPlayer.plugin.getConfig().set("Region." + args[1], null);
                    MusicPackPlayer.plugin.saveConfig();
                    player.sendMessage(ChatColor.YELLOW + "Musique de r" + '\u00E9' + "gion " + args[1] + " supprim" + '\u00E9' + "e");
                } else {
                    player.sendMessage(ChatColor.RED + "Cette r" + '\u00E9' + "gion n'existe pas dans la configuration!");
                }
            }
        } else {
            player.sendMessage(ChatColor.RED + "Vous devez donner un nom de r" + '\u00E9' + "gion" + '\u00E0' + "supprimer!");
        }
    }
}
