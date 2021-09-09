package net.mov51.lightmaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.Plugin;

import static net.mov51.lightmaker.util.Lights.*;

public class Recipe {

    public static void addBasicRecipe(Plugin plugin){

        NamespacedKey key = new NamespacedKey(plugin, "light_block");
        ShapelessRecipe recipe = new ShapelessRecipe(key, lights.get(15));

        recipe.addIngredient(Material.GLOW_INK_SAC);
        recipe.addIngredient(Material.GLASS);
        recipe.addIngredient(Material.GLOWSTONE_DUST);
        recipe.addIngredient(Material.GLOWSTONE_DUST);
        recipe.addIngredient(Material.STRING);
        recipe.addIngredient(Material.STRING);

        Bukkit.addRecipe(recipe);
    }

    public static void addLevelRecipe(Plugin plugin){
        for (int i = 15; i >= 0; i--){
            NamespacedKey key = new NamespacedKey(plugin, "light_count_" + i);
            StonecuttingRecipe recipe = new StonecuttingRecipe(key, lights.get(i), Material.LIGHT);
            Bukkit.getServer().addRecipe(recipe);
        }
    }




}
