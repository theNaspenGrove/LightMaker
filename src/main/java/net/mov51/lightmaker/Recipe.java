package net.mov51.lightmaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

import static net.mov51.lightmaker.LightMaker.recipeList;
import static net.mov51.lightmaker.util.Lights.*;

public class Recipe {

    public static void addRecipes(Plugin plugin){
        String recipe_type = plugin.getConfig().getString("recipe_type");
        assert recipe_type != null;

        if(recipe_type.equals("bottled-light")){
            addBottlingRecipe(plugin);
        }else if(recipe_type.equals("shapeless")){
            addShapelessRecipe(plugin);
        }

    }

    public static void addShapelessRecipe(Plugin plugin){
        plugin.getLogger().log(Level.INFO,"Adding Shapeless Light Block recipe!");
        //create a new unique key
        NamespacedKey key = new NamespacedKey(plugin, "light_block_shapeless");
        //creates a new recipe with the default light quantity as the output quantity
        ShapelessRecipe recipe = new ShapelessRecipe(key, lights.get(15).asQuantity(plugin.getConfig().getInt("default_light_quantity")));
        //places the light_ingredients configuration list into a String List
        List<String> light_ingredients = plugin.getConfig().getStringList("light_ingredients");
        //ensures that the list can fit into a crafting grid
        if(light_ingredients.size() <= 9){
            //loops through the light_ingredients list
            for(String ingredient : light_ingredients){
                try {
                    //tries to convert the name provided to a material. https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
                    recipe.addIngredient(Material.valueOf(ingredient.toUpperCase()));
                }catch (IllegalArgumentException e){
                    //if it can't be converted, notify and exit method
                    plugin.getLogger().log(Level.SEVERE,ingredient + " does not exist and the Shapeless Light Block recipe can not be created!");
                    return;
                }
            }
        }
        plugin.getLogger().log(Level.INFO,"Shapeless Light Block recipe added!");
        Bukkit.addRecipe(recipe);
    }

    public static void addBottlingRecipe(Plugin plugin){
        plugin.getLogger().log(Level.INFO,"Adding Light Block bottling recipe!");
        //the name/key of the group used for collecting the recipes in the recipe book.
        String bottlingGroup = "light_block_bottling";
        //loops through each item in the 'lights_to_bottle' section and creates a recipe with the defined quantity in a group.
        for(String ingredient : Objects.requireNonNull(plugin.getConfig().getConfigurationSection("light_ingredients_to_bottle")).getKeys(false)){
            //if the quantity for the current entry is zero or doesn't exist, use the default quantity.
            int q = plugin.getConfig().getInt("light_ingredients_to_bottle." + ingredient + ".quantity") != 0 ?
                    plugin.getConfig().getInt("light_ingredients_to_bottle." + ingredient + ".quantity") :
                    plugin.getConfig().getInt("default_light_quantity");
            //create a new unique key with the ingredient name.
            NamespacedKey key = new NamespacedKey(plugin, "light_block_bottling_" + ingredient);
            Material itemToBottle;

            try {
                //try to convert the name provided to a material. https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
                itemToBottle = Material.valueOf(ingredient.toUpperCase());
            }catch (IllegalArgumentException e){
                //if it can't be converted, notify and skip to the next entry
                plugin.getLogger().log(Level.SEVERE,ingredient + " does not exist and the light block recipe can not be created!");
                continue;
            }
            //create a shapeless recipe with the defined quantity as the output quantity.
            ShapelessRecipe bottleRecipe = new ShapelessRecipe(key,
                    lights.get(15).asQuantity(q));

            //add a glass bottle to the recipe
            bottleRecipe.addIngredient(Material.GLASS_BOTTLE);
            //add the provided entry as an ingredient
            bottleRecipe.addIngredient(itemToBottle);
            //Adding this recipe to bottlingGroup that collects in the crafting book.
            bottleRecipe.setGroup(bottlingGroup);
            //add the recipe to the server
            Bukkit.addRecipe(bottleRecipe);
            plugin.getLogger().log(Level.INFO,"ingredient can now be bottled!");
        }
        plugin.getLogger().log(Level.INFO,"Light Block bottling recipes have been added!");
    }

    public static void addLevelRecipe(Plugin plugin){
        for (int i = 15; i >= 0; i--){
            NamespacedKey key = new NamespacedKey(plugin, "light_count_" + i);
            StonecuttingRecipe recipe = new StonecuttingRecipe(key, lights.get(i), Material.LIGHT);
            Bukkit.getServer().addRecipe(recipe);
        }
    }




}
