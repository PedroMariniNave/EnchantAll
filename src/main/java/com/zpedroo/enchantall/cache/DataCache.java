package com.zpedroo.enchantall.cache;

import com.zpedroo.enchantall.objects.EnchantmentItem;
import com.zpedroo.enchantall.utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCache {

    private static final List<EnchantmentItem> enchantmentItems = getEnchantmentItemsFromConfig();
    private static final Map<Enchantment, String> translations = getTranslationsFromConfig();

    public static EnchantmentItem getEnchantmentItem(Material material) {
        for (EnchantmentItem enchantmentItem : enchantmentItems) {
            if (enchantmentItem.getMaterials().contains(material)) return enchantmentItem;
        }

        return null;
    }

    public static List<EnchantmentItem> getEnchantmentItems() {
        return enchantmentItems;
    }

    public static Map<Enchantment, String> getTranslations() {
        return translations;
    }

    private static List<EnchantmentItem> getEnchantmentItemsFromConfig() {
        List<EnchantmentItem> ret = new ArrayList<>(8);
        FileUtils.Files file = FileUtils.Files.CONFIG;

        for (String str : FileUtils.get().getSection(file, "Items")) {
            String displayName = ChatColor.translateAlternateColorCodes('&', FileUtils.get().getString(file, "Items." + str + ".display-name"));
            List<String> enchantmentsNames = FileUtils.get().getStringList(file, "Items." +str + ".enchantments");
            List<String> materialsNames = FileUtils.get().getStringList(file, "Items." +str + ".materials");

            List<Material> materials = new ArrayList<>(materialsNames.size());
            List<Enchantment> enchantments = new ArrayList<>(enchantmentsNames.size());

            for (String materialName : materialsNames) {
                Material material = Material.getMaterial(materialName.toUpperCase());
                if (material == null) continue;

                materials.add(material);
            }

            for (String enchantmentName : enchantmentsNames) {
                Enchantment enchantment = Enchantment.getByName(enchantmentName.toUpperCase());
                if (enchantment == null) continue;

                enchantments.add(enchantment);
            }

            ret.add(new EnchantmentItem(displayName, materials, enchantments));
        }

        return ret;
    }

    private static Map<Enchantment, String> getTranslationsFromConfig() {
        Map<Enchantment, String> ret = new HashMap<>(8);
        FileUtils.Files file = FileUtils.Files.CONFIG;

        for (String enchantmentName : FileUtils.get().getSection(file, "Translations")) {
            Enchantment enchantment = Enchantment.getByName(enchantmentName.toUpperCase());
            if (enchantment == null) continue;

            String translation = ChatColor.translateAlternateColorCodes('&', FileUtils.get().getString(file, "Translations." + enchantmentName));
            ret.put(enchantment, translation);
        }

        return ret;
    }
}