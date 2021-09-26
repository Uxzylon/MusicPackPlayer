package com.gmail.anthony17j.musicpackplayer.command;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class subCommand {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract List<String> getSubcommandArguments(Player player, String args[]);
    public abstract void perform(Player player, String args[]);
}
