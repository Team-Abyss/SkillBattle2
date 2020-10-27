package com.jp.ray.spigot.skillbattle.api;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface SkillClass {

    String getName();

    ItemStack getIcon();

    List<Skill> getSkills();

    int getHP();

    double getSpeed();
}
