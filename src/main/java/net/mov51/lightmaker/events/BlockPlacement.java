package net.mov51.lightmaker.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static net.mov51.lightmaker.util.ParticleRender.summonMarker;

public class BlockPlacement implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        if(e.getBlock().getType() == Material.LIGHT && !e.isCancelled()){
            summonMarker(e.getPlayer(),e.getBlock());
        }
    }

}
