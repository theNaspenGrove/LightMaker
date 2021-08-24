package net.mov51.lightmaker.util;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
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

    private static ItemStack makeLight(int i){
        ItemStack light = new ItemStack(Material.LIGHT);
        ItemMeta lightMeta = light.getItemMeta();
        BlockData data = Material.LIGHT.createBlockData();
        ((Light) data).setLevel(i);
        ((BlockDataMeta) lightMeta).setBlockData(data);
        light.setItemMeta(lightMeta);
        return light;
    }

    public static int getLight(ItemStack light){
        ItemMeta lightMeta = light.getItemMeta();
        BlockData data = ((BlockDataMeta) lightMeta).getBlockData(Material.LIGHT);
        return ((Light) data).getLevel();
    }

    public static int rollOverAdd(int i){
        if(i > 15)
            return i - 15;
        return i;
    }
}
