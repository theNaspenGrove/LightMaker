package net.mov51.lightmaker;

import net.mov51.lightmaker.events.*;
import net.mov51.lightmaker.util.Lights;
import net.mov51.lightmaker.util.Highlighter;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static net.mov51.lightmaker.Recipe.*;
import static net.mov51.lightmaker.util.HandWatcher.startWatching;

public final class LightMaker extends JavaPlugin {

    public static Highlighter projector;

    public static List<NamespacedKey> recipeList = new ArrayList<>();

    public static int watchPeriod;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteraction(), this);
        getServer().getPluginManager().registerEvents(new BlockReplacement(), this);
        getServer().getPluginManager().registerEvents(new BlockPlacement(), this);
        Lights.makeLights();
        projector = new Highlighter(this);

        this.saveDefaultConfig();
        //If setting is true, register player log-in event.
        if(this.getConfig().getBoolean("grant_recipes_on_login")){getServer().getPluginManager().registerEvents(new PlayerLogin(), this);}
        if(this.getConfig().getBoolean("stop-entity-spawns-at-0")){getServer().getPluginManager().registerEvents(new CreatureSpawnEventListener(), this);}
        watchPeriod = this.getConfig().getInt("watch-period-in-ticks") != 0 ? this.getConfig().getInt("watch-period-in-ticks") : 10;
        startWatching(this);
        addRecipes(this);
        addLevelRecipe(this);

        this.getLogger().log(Level.INFO,"Lights can now be MADE by *your* hands!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
