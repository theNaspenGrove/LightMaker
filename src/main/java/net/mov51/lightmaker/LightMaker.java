package net.mov51.lightmaker;

import net.mov51.lightmaker.events.BlockReplacement;
import net.mov51.lightmaker.events.PlayerInteraction;
import net.mov51.lightmaker.events.PlayerLogin;
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

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteraction(), this);
        getServer().getPluginManager().registerEvents(new BlockReplacement(), this);
        Lights.makeLights();
        projector = new Highlighter(this);

        this.saveDefaultConfig();
        //If setting is true, register player log-in event.
        if(this.getConfig().getBoolean("grant_recipes_on_login")){getServer().getPluginManager().registerEvents(new PlayerLogin(), this);}

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
