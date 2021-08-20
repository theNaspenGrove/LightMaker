package net.mov51.lightmaker.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class HandWatcher {

    public static final ItemStack light = new ItemStack(Material.LIGHT);

    public static void startWatching(Plugin plugin){
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    ItemStack hand = p.getInventory().getItemInMainHand();
                    Block b = p.getTargetBlock(null,10);
                    if(hand.isSimilar(light) && b != null && b.getType() == Material.LIGHT) {
                        int levelledBlock = ((Levelled) b.getBlockData()).getLevel();
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(String.valueOf(levelledBlock)));
                    }
                }
            }
        }.runTaskTimer(plugin, 5L, 5L);
    }
}

