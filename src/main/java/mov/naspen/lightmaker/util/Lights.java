package mov.naspen.lightmaker.util;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Lights {
    public static ArrayList<ItemStack> lights = new ArrayList<>();

    public static void makeLights(){
        for(int i = 0; i < 16; i++){
            lights.add(makeLight(i));
        }
    }

    public static ItemStack makeLight(int i){
        ItemStack light = new ItemStack(Material.LIGHT);
        ItemMeta lightMeta = light.getItemMeta();
        BlockData data = Material.LIGHT.createBlockData();
        ((Light) data).setLevel(i);
        ((BlockDataMeta) lightMeta).setBlockData(data);
        light.setItemMeta(lightMeta);

        return light;
    }

    public static int getLightLevel(ItemStack light){
        ItemMeta lightMeta = light.getItemMeta();
        BlockData data = ((BlockDataMeta) lightMeta).getBlockData(Material.LIGHT);
        return ((Light) data).getLevel();
    }

    public static boolean isLight(ItemStack itemStack){
        return itemStack.getType() == Material.LIGHT;
    }

}
