package net.mov51.lightmaker.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.mov51.lightmaker.util.Lights.*;

public class PlayerInteraction implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent e){

        if(e.getItem() != null && e.getClickedBlock() != null &&lights.contains(e.getItem().asOne())){
            //get Block
            Block b = e.getClickedBlock();
            //check block type
            if(b.getType() == Material.LIGHT){
                //get block level
                int i = ((Levelled) b.getBlockData()).getLevel();
                //break
                if(e.getAction() == Action.LEFT_CLICK_BLOCK){
                    b.breakNaturally();
                    b.getWorld().dropItemNaturally(b.getLocation(),lights.get(i));
                //add
                }else if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    //get hand level
                    int handLevel = getLight(e.getItem().asOne());
                    if(handLevel != 15 & handLevel != 0){
                        e.setCancelled(true);
                        BlockData data = b.getBlockData();
                        ((Levelled) data).setLevel(rollOverAdd(i + handLevel ));
                        b.setBlockData(data);
                    }
                }
            }
        }
    }
}
