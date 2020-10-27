package com.jp.ray.spigot.skillbattle.simple.classes;

import com.jp.ray.spigot.skillbattle.api.Skill;
import com.jp.ray.spigot.skillbattle.api.SkillClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Saber implements SkillClass {

    private final ItemStack icon = createIcon();
    private ItemStack createIcon() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§a§lセイバー");
        meta.setLore(Arrays.asList("近接攻撃に優れたクラス","体力,移動速度に優れている","クールタイムは総じて長め"));
        meta.addEnchant(Enchantment.ARROW_DAMAGE,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public String getName() {
        return "セイバー";
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public List<Skill> getSkills() {
        return null;
    }

    @Override
    public int getHP() {
        return 30;
    }

    @Override
    public double getSpeed() {
        return 1;
    }
}
