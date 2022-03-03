package net.mov51.lightmaker.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import static net.mov51.lightmaker.LightMaker.recipeList;

public class PlayerLogin implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void PlayerLoginEvent(PlayerLoginEvent e) {
        //load player with recipes when the player logs in.
        e.getPlayer().discoverRecipes(recipeList);
    }
}
