package net.mov51.lightmaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import static net.mov51.lightmaker.util.Lights.lights;

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
        for(int i = 0; i < 15; i++){
            addRecipe(plugin,i,i + 1);
        }
        addRecipe(plugin,15,0);
    }



    private static void addRecipe(Plugin plugin, int from, int too){
        String group = "Light Blocks";
        NamespacedKey key = new NamespacedKey(plugin, "leveled_light_block_"+too);
        ShapelessRecipe recipe = new ShapelessRecipe(key, lights.get(too));
        recipe.addIngredient((lights.get(from)));
        recipe.setGroup(group);
        Bukkit.addRecipe(recipe);
    }

}
