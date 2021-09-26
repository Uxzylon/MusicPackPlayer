package com.gmail.anthony17j.musicpackplayer.command.subcommands;

import com.gmail.anthony17j.musicpackplayer.MusicPackPlayer;
import com.gmail.anthony17j.musicpackplayer.command.subCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class reload extends subCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload config";
    }

    @Override
    public String getSyntax() {
        return "/musicpack reload";
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public void perform(Player player, String[] args) {
        MusicPackPlayer.plugin.reloadConfig();
        player.sendMessage(ChatColor.YELLOW + "Config reloaded!");
        MusicPackPlayer.plugin.getLogger().info("Config reloaded!");
    }
}
