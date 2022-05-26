package com.zpedroo.enchantall.objects;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class EnchantmentItem {

    private final String displayName;
    private final List<Material> materials;
    private final List<Enchantment> enchantments;

    public EnchantmentItem(String displayName, List<Material> materials, List<Enchantment> enchantments) {
        this.displayName = displayName;
        this.materials = materials;
        this.enchantments = enchantments;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }
}