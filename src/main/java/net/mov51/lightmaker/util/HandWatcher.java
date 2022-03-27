package net.mov51.lightmaker.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.mov51.lightmaker.LightMaker.projector;
import static net.mov51.lightmaker.util.Lights.isLight;


public class HandWatcher {

    public static void startWatching(Plugin plugin){
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(isLight(p.getInventory().getItemInMainHand())){
                        projector.add(p);
                    }else{
                        projector.remove(p);
                    }
                }
            }
        }.runTaskTimer(plugin, 2L, 2L);
    }
}

