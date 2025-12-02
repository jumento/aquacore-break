package com.aquacore.breakplugin.listeners;

import com.aquacore.breakplugin.AquaCoreBreak;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    private final AquaCoreBreak plugin;

    public ExplosionListener(AquaCoreBreak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (!plugin.getConfig().getBoolean("explosion-drops", true)) {
            event.setYield(0.0f);
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        if (!plugin.getConfig().getBoolean("explosion-drops", true)) {
            event.setYield(0.0f);
        }
    }
}
