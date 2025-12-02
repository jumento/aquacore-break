package com.aquacore.breakplugin.commands;

import com.aquacore.breakplugin.AquaCoreBreak;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
                sender.sendMessage(
                        Component.text("You do not have permission to use this command.", NamedTextColor.RED));
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(Component.text("AquaCoreBreak configuration reloaded!", NamedTextColor.GREEN));
            return true;
        }

        sender.sendMessage(Component.text("Usage: /acb reload", NamedTextColor.RED));
        return true;
    }
}
