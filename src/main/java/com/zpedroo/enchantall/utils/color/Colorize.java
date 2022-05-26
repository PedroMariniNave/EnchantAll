package com.zpedroo.enchantall.utils.color;

import org.bukkit.ChatColor;

public class Colorize {

    public static String getColored(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}