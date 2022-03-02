package net.mov51.lightmaker;

import net.mov51.lightmaker.events.BlockReplacement;
import net.mov51.lightmaker.events.PlayerInteraction;
import net.mov51.lightmaker.util.Lights;
import net.mov51.lightmaker.util.Highlighter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static net.mov51.lightmaker.Recipe.addBasicRecipe;
import static net.mov51.lightmaker.Recipe.addLevelRecipe;
import static net.mov51.lightmaker.util.HandWatcher.startWatching;

public final class LightMaker extends JavaPlugin {

    public static Highlighter projector;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteraction(), this);
        getServer().getPluginManager().registerEvents(new BlockReplacement(), this);
        Lights.makeLights();
        projector = new Highlighter(this);

        this.saveDefaultConfig();

        startWatching(this);
        addBasicRecipe(this);
        addLevelRecipe(this);

        this.getLogger().log(Level.INFO,"Lights can be MADE");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
