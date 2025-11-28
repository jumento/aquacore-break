package com.aquacore.breakplugin.listeners;

import com.aquacore.breakplugin.AquaCoreBreak;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class BreakListener implements Listener {

    private final AquaCoreBreak plugin;
    private final Random random = new Random();

    public BreakListener(AquaCoreBreak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        // 1. Check Creative Mode
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        // 2. Check Enabled Worlds
        List<String> enabledWorlds = plugin.getConfig().getStringList("enabled-worlds");
        if (!enabledWorlds.contains(player.getWorld().getName())) {
            return;
        }

        // 3. Check Tool
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        List<String> toolItems = plugin.getConfig().getStringList("tool-items");
        if (toolItems.contains(itemInHand.getType().name())) {
            return;
        }

        // 4. Check Hand-Breakable Blocks - REMOVED to enforce whitelist for ALL blocks
        Material blockType = event.getBlock().getType();
        // List<String> handBreakableBlocks =
        // plugin.getConfig().getStringList("hand-breakable-blocks");
        // if (!handBreakableBlocks.contains(blockType.name())) {
        // return;
        // }

        // 5. Check Whitelist Logic
        ConfigurationSection whitelist = plugin.getConfig().getConfigurationSection("whitelist");
        if (whitelist == null || !whitelist.contains(blockType.name())) {
            // Not in whitelist -> Break but no drops
            event.setDropItems(false);
            return;
        }

        ConfigurationSection blockConfig = whitelist.getConfigurationSection(blockType.name());
        if (blockConfig == null) {
            event.setDropItems(false);
            return;
        }

        String mode = blockConfig.getString("mode", "DEFAULT");

        switch (mode) {
            case "DEFAULT":
                // Do nothing, let vanilla drops happen
                break;
            case "NONE":
                event.setDropItems(false);
                break;
            case "COMMAND":
                event.setDropItems(false);
                String command = blockConfig.getString("command");
                if (command != null) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
                }
                break;
            case "CUSTOM":
                event.setDropItems(false);
                ConfigurationSection drops = blockConfig.getConfigurationSection("drops");
                if (drops != null) {
                    for (String key : drops.getKeys(false)) {
                        ConfigurationSection dropSection = drops.getConfigurationSection(key);
                        if (dropSection != null) {
                            double chance = dropSection.getDouble("chance", 0.0);
                            if (random.nextDouble() <= chance) {
                                String itemMaterial = dropSection.getString("item");
                                int amount = dropSection.getInt("amount", 1);
                                if (itemMaterial != null) {
                                    Material mat = Material.getMaterial(itemMaterial);
                                    if (mat != null) {
                                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                                                new ItemStack(mat, amount));
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            default:
                // Fallback to no drops if mode is invalid
                event.setDropItems(false);
                break;
        }
    }
}
