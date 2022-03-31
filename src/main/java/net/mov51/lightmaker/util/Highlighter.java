package net.mov51.lightmaker.util;

import net.mov51.lightmaker.LightMaker;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Highlighter {

    protected LightMaker plugin;
    private Map<UUID, ParticleRender> particleMapper = new HashMap<>();
    private BukkitTask projectorTask;

    public Highlighter(LightMaker plugin) {
        this.plugin = plugin;
        start();
    }

    public void start() {
        if (projectorTask != null) {
            projectorTask.cancel();
            projectorTask = null;
        }
        int updateRate = 1;
        int watchPeriod = plugin.getConfig().getInt("watch-period-in-ticks");
        projectorTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (ParticleRender visual : particleMapper.values()) {
                    visual.update();
                }
            }
        }.runTaskTimer(plugin, updateRate, watchPeriod);
    }

    public void add(Player player) {
        if (player == null || this.contains(player.getUniqueId())) return;
        ParticleRender visual = new ParticleRender(this, player.getUniqueId());
        particleMapper.put(player.getUniqueId(), visual);
    }

    public void remove(Player player) {
        if (player == null) return;
        this.remove(player.getUniqueId());
    }

    public void remove(UUID uuid) {
        particleMapper.remove(uuid);
    }

    public boolean contains(UUID uuid) {
        return particleMapper.containsKey(uuid);
    }
}
