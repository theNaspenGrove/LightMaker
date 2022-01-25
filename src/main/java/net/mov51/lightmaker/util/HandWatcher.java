package net.mov51.lightmaker.util;

import net.kyori.adventure.text.Component;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.mov51.lightmaker.LightMaker.projector;
import static net.mov51.lightmaker.util.Lights.*;

public class HandWatcher {

    public static void startWatching(Plugin plugin){
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    ItemStack hand = p.getInventory().getItemInMainHand();

                    if(lights.contains(hand.asOne())){
                        projector.add(p);
                    }else{
                        projector.remove(p);
                    }
                }
            }
        }.runTaskTimer(plugin, 2L, 2L);
    }
}

