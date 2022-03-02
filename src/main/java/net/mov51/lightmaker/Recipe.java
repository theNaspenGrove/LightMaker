package net.mov51.lightmaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.type.Light;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.logging.Level;

import static net.mov51.lightmaker.util.Lights.*;

public class Recipe {

    public static void addBasicRecipe(Plugin plugin){
        plugin.getLogger().log(Level.INFO,"Adding Light Block recipe!");
        NamespacedKey key = new NamespacedKey(plugin, "light_block");
        ShapelessRecipe recipe = new ShapelessRecipe(key, lights.get(15).asQuantity(plugin.getConfig().getInt("light_quantity")));
        List<String> light_ingredients = plugin.getConfig().getStringList("light_ingredients");

        if(light_ingredients.size() <= 9){
            for(String ingredient : light_ingredients){
                try {
                    recipe.addIngredient(Material.valueOf(ingredient.toUpperCase()));
                }catch (IllegalArgumentException e){
                    plugin.getLogger().log(Level.SEVERE,ingredient + " does not exist and the light block recipe can not be created!");
                    return;
                }
            }
        }
        plugin.getLogger().log(Level.INFO,"Light Block recipe added!");
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
