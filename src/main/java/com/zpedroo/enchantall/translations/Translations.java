package com.zpedroo.enchantall.translations;

import com.zpedroo.enchantall.cache.DataCache;
import org.bukkit.enchantments.Enchantment;

public class Translations {

    public static String getEnchantmentTranslation(Enchantment enchantment) {
        return DataCache.getTranslations().get(enchantment);
    }
}