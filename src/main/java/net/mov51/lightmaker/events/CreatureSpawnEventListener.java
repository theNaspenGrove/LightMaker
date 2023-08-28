package net.mov51.lightmaker.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Light;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreatureSpawnEventListener implements Listener {
    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
        if(e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL ||
                e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.REINFORCEMENTS ||
                e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.RAID ||
                e.getSpawnReason() !=CreatureSpawnEvent.SpawnReason.VILLAGE_INVASION){return;}
        //Check if mob intersects with any lights
        List<Location> toCheck = new ArrayList<>();
        for (double x = Math.floor(e.getEntity().getBoundingBox().getMinX()); x <= Math.ceil(e.getEntity().getBoundingBox().getMaxX()); x++) {
            for (double y = Math.floor(e.getEntity().getBoundingBox().getMinY()); y <= Math.ceil(e.getEntity().getBoundingBox().getMaxY()); y++) {
                for (double z = Math.floor(e.getEntity().getBoundingBox().getMinZ()); z <= Math.ceil(e.getEntity().getBoundingBox().getMaxZ()); z++) {
                    toCheck.add(new Location(e.getEntity().getWorld(), x, y, z));
                }
            }
        }
        for(Location l : toCheck){
            Block block = l.getBlock();
            if(block.getType() == org.bukkit.Material.LIGHT && ((Light)block.getBlockData()).getLevel() == 0){
                e.setCancelled(true);
                return;
            }
        }
    }
}
