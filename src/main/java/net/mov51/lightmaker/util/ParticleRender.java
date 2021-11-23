package net.mov51.lightmaker.util;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.mov51.lightmaker.util.Lights.getLightLevelInHand;

public class ParticleRender {

    private final UUID PLAYER_UUID;
    private Highlighter highlight;
    private Player player;

    Color[] lightColors = {
            Color.fromRGB(252,0,48),
            Color.fromRGB(239,0,105),
            Color.fromRGB(217,0,150),
            Color.fromRGB(192,0,184),
            Color.fromRGB(160,0,211),
            Color.fromRGB(109,0,233),
            Color.fromRGB(53,31,246),
            Color.fromRGB(24,33,254),
            Color.fromRGB(33,63,254),
            Color.fromRGB(82,105,246),
            Color.fromRGB(125,139,231),
            Color.fromRGB(158,164,213),
            Color.fromRGB(192,191,184),
            Color.fromRGB(217,212,152),
            Color.fromRGB(236,225,117),
            Color.fromRGB(255,242,32)
            };

    public ParticleRender(Highlighter projector, UUID uuid) {
        this.highlight = projector;
        PLAYER_UUID = uuid;
        player = Bukkit.getPlayer(uuid);
    }

    public void update() {
        render();
    }

    private void render() {
        if (this.player == null) {
            highlight.remove(PLAYER_UUID);
            return;
        }
        Location loc = player.getLocation().clone();
        World world = loc.getWorld();
        int px = loc.getBlockX(), py = loc.getBlockY(), pz = loc.getBlockZ();

        int radA = 10;
        int radH = 10;

        for (int z = -radA; z <= radA; z++) {

            for (int x = -radA; x <= radA; x++) {

                for (int y = -radH; y <= radH; y++) {
                    if (Math.sqrt((x * x) + (y * y) + (z * z)) > radA) continue;
                    assert world != null;
                    Block block = world.getBlockAt(px + x, py + y, pz + z);

                    if(block.getType() == Material.LIGHT){

                        int level = ((Levelled) block.getBlockData()).getLevel();
                        Particle.DustOptions dustOptions;

                        if(getLightLevelInHand(player) == level){
                            dustOptions = new Particle.DustOptions(Color.fromRGB(25,214,0), 1.0F);
                        }else{
                            dustOptions = new Particle.DustOptions(lightColors[level], 1.0F);
                        }

                        player.spawnParticle(Particle.REDSTONE, block.getLocation().add(0.5,0.5,0.5), 50, dustOptions);
                    }

                }
            }
        }
    }
}
