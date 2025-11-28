package com.aquacore.breakplugin.commands;

import com.aquacore.breakplugin.AquaCoreBreak;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final AquaCoreBreak plugin;

    public ReloadCommand(AquaCoreBreak plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("aquacorebreak.admin")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "AquaCoreBreak configuration reloaded!");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Usage: /acb reload");
        return true;
    }
}
