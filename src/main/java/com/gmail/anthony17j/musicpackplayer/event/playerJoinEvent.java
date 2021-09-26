package com.gmail.anthony17j.musicpackplayer.event;

import com.gmail.anthony17j.musicpackplayer.MusicPackPlayer;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class playerJoinEvent implements Listener {

    MusicPackPlayer plugin = MusicPackPlayer.plugin;

    @EventHandler
    public void onEvent (PlayerJoinEvent event) {

        Player player = event.getPlayer();
        final String[] currentRegion = {""};

        new BukkitRunnable() {
            @Override
            public void run() {
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionQuery query = container.createQuery();
                ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));

                if (set.getRegions().isEmpty()) {
                    if (!currentRegion[0].equalsIgnoreCase("")) {
                        //player.sendMessage(ChatColor.YELLOW + "You exited " + currentRegion[0] + " region!");
                        String regionSong = getRegionSong(currentRegion[0]);
                        if (regionSong != null) {
                            player.stopSound(regionSong);
                        }
                        currentRegion[0] = "";
                    }
                } else {
                    for (ProtectedRegion region : set) {
                        String regionString = region.getId();
                        if (!currentRegion[0].equalsIgnoreCase(regionString)) {
                            String curRegionSong = getRegionSong(currentRegion[0]);
                            if (!currentRegion[0].equalsIgnoreCase("") && curRegionSong != null) {
                                player.stopSound(curRegionSong);
                            }
                            currentRegion[0] = regionString;
                            //player.sendMessage(ChatColor.YELLOW + "You are in the " + regionString + " region!");
                            String regionSong = getRegionSong(regionString);
                            if (regionSong != null) {
                                player.playSound(player.getLocation(),regionSong,500,1);
                            }
                        }
                    }

                }
            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    public String getRegionSong (String region) {
        String str = plugin.getConfig().getString("Region." + region);
        if (str == null) {
            return str;
        } else {
            return str;
        }
    }
}
