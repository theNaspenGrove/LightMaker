package net.mov51.lightmaker.events;

import org.bukkit.Material;
import org.bukkit.block.data.type.Light;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static net.mov51.lightmaker.util.Lights.makeLight;

public class BlockReplacement implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        if(e.getBlockReplacedState().getType() == Material.LIGHT){
           e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), makeLight(((Light) e.getBlockReplacedState().getBlockData()).getLevel()));
        }
    }
}
