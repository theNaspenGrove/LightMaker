package mov.naspen.lightmaker.util;

import mov.naspen.lightmaker.LightMaker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class HandWatcher {



    public static void startWatching(Plugin plugin){

        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(Lights.isLight(p.getInventory().getItemInMainHand()) || Lights.isLight(p.getInventory().getItemInOffHand())){
                        LightMaker.projector.add(p);
                    }else{
                        LightMaker.projector.remove(p);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, LightMaker.watchPeriod);
    }
}

