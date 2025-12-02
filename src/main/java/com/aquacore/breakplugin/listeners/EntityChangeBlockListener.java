package com.aquacore.breakplugin.listeners;

import com.aquacore.breakplugin.AquaCoreBreak;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlockListener implements Listener {

    private final AquaCoreBreak plugin;

    public EntityChangeBlockListener(AquaCoreBreak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.ENDERMAN) {
            if (!plugin.getConfig().getBoolean("enderman-pickup", true)) {
                // Enderman picking up a block changes it to AIR
                if (event.getTo() == Material.AIR) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
