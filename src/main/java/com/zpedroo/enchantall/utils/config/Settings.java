package com.zpedroo.enchantall.utils.config;

import com.zpedroo.enchantall.utils.FileUtils;
import com.zpedroo.enchantall.utils.color.Colorize;

import java.util.List;

public class Settings {

    public static final String COMMAND = FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.command");

    public static final List<String> ALIASES = FileUtils.get().getStringList(FileUtils.Files.CONFIG, "Settings.aliases");

    public static final String PERMISSION = FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.permission");

    public static final String PERMISSION_MESSAGE = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.permission-message"));

    public static final boolean USE_ROMAN_NUMBERS = FileUtils.get().getBoolean(FileUtils.Files.CONFIG, "Settings.use-roman-numbers");

    public static final String LORE_FORMAT = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.lore-format"));
}