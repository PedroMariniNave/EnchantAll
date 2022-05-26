package com.zpedroo.enchantall;

import com.zpedroo.enchantall.commands.EnchantAllCmd;
import com.zpedroo.enchantall.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import static com.zpedroo.enchantall.utils.config.Settings.*;

public class EnchantAll extends JavaPlugin {

    private static EnchantAll instance;
    public static EnchantAll get() { return instance; }

    public void onEnable() {
        instance = this;
        new FileUtils(this);

        registerCommand(COMMAND, ALIASES, PERMISSION, PERMISSION_MESSAGE, new EnchantAllCmd());
    }

    private void registerCommand(String command, List<String> aliases, String permission, String permissionMessage, CommandExecutor executor) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            PluginCommand pluginCmd = constructor.newInstance(command, this);
            pluginCmd.setAliases(aliases);
            pluginCmd.setExecutor(executor);
            pluginCmd.setPermission(permission);
            pluginCmd.setPermissionMessage(permissionMessage);

            Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
            commandMap.register(getName().toLowerCase(), pluginCmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
