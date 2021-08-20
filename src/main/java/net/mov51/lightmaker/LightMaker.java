package net.mov51.lightmaker;

import net.mov51.lightmaker.events.PlayerInteraction;
import org.bukkit.plugin.java.JavaPlugin;

import static net.mov51.lightmaker.util.HandWatcher.startWatching;

public final class LightMaker extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteraction(), this);
        startWatching(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
