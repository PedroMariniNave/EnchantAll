package com.zpedroo.enchantall.commands;

import com.zpedroo.enchantall.cache.DataCache;
import com.zpedroo.enchantall.objects.EnchantmentItem;
import com.zpedroo.enchantall.translations.Translations;
import com.zpedroo.enchantall.utils.color.Colorize;
import com.zpedroo.enchantall.utils.config.Messages;
import com.zpedroo.enchantall.utils.config.Settings;
import com.zpedroo.enchantall.utils.roman.NumberConverter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantAllCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        if (args.length < 1) {
            sender.sendMessage(StringUtils.replaceEach(Messages.COMMAND_USAGE, new String[]{
                    "{cmd}"
            }, new String[]{
                    label
            }));
            return true;
        }

        int level = 0;
        try {
            level = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            // ignore
        }

        if (level <= 0) {
            sender.sendMessage(Messages.INVALID_LEVEL);
            return true;
        }

        Player player = (Player) sender;
        StringBuilder displayName = new StringBuilder();
        if (args.length >= 2) {
            for (int i = 1; i < args.length; ++i) {
                if (displayName.length() > 0) displayName.append(" ");

                displayName.append(Colorize.getColored(args[i]));
            }
        }

        for (ItemStack items : player.getInventory().getContents()) {
            enchantItem(items, level, displayName.toString());
        }

        for (ItemStack armor : player.getInventory().getArmorContents()) {
            enchantItem(armor, level, displayName.toString());
        }

        player.updateInventory();
        return false;
    }

    private void enchantItem(ItemStack item, int level, String displayName) {
        if (item == null || item.getType().equals(Material.AIR)) return;

        EnchantmentItem enchantmentItem = DataCache.getEnchantmentItem(item.getType());
        if (enchantmentItem == null) return;

        List<Enchantment> enchantments = enchantmentItem.getEnchantments();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(enchantments.size());
        for (Enchantment enchantment : enchantments) {
            meta.addEnchant(enchantment, level, true);
            lore.add(StringUtils.replaceEach(Settings.LORE_FORMAT, new String[]{
                    "{enchantment}",
                    "{level}"
            }, new String[]{
                    Translations.getEnchantmentTranslation(enchantment) == null ? enchantment.getName() : Translations.getEnchantmentTranslation(enchantment),
                    Settings.USE_ROMAN_NUMBERS ? NumberConverter.convertToRoman(level) : String.valueOf(level)
            }));
        }

        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        if (displayName != null && !displayName.isEmpty()) {
            meta.setDisplayName(StringUtils.replaceEach(enchantmentItem.getDisplayName(), new String[]{
                    "{text}"
            }, new String[]{
                    displayName
            }));
        }

        item.setItemMeta(meta);
    }
}
