package net.mov51.lightmaker.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerInteraction implements Listener {

    public static final ItemStack light = new ItemStack(Material.LIGHT);

    @EventHandler(priority = EventPriority.NORMAL)
    public void PlayerInteractEvent(PlayerInteractEvent e){
        if(e.getAction() == Action.LEFT_CLICK_BLOCK && Objects.requireNonNull(e.getClickedBlock()).getType() == Material.LIGHT){
            Block b = e.getClickedBlock();
            b.breakNaturally();
            b.getWorld().dropItemNaturally(b.getLocation(),light);
        }
    }
}
