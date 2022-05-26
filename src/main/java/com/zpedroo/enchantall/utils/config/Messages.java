package com.zpedroo.enchantall.utils.config;

import com.zpedroo.enchantall.utils.FileUtils;
import com.zpedroo.enchantall.utils.color.Colorize;

public class Messages {

    public static final String COMMAND_USAGE = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.command-usage"));

    public static final String INVALID_LEVEL = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.invalid-level"));
}