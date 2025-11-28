package com.aquacore.breakplugin;

import com.aquacore.breakplugin.commands.ReloadCommand;
import com.aquacore.breakplugin.listeners.BreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AquaCoreBreak extends JavaPlugin {

    @Override
    public void onEnable() {
        // Save default config
        saveDefaultConfig();

        // Register commands
        getCommand("acb").setExecutor(new ReloadCommand(this));

        // Register listeners
        getServer().getPluginManager().registerEvents(new BreakListener(this), this);

        getLogger().info("AquaCoreBreak has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AquaCoreBreak has been disabled!");
    }
}
