package com.jp.ray.spigot.skillbattle;

import com.jp.ray.spigot.skillbattle.simple.Command;
import com.jp.ray.spigot.skillbattle.simple.listener.InventoryClickListener;
import com.jp.ray.spigot.skillbattle.simple.listener.InventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillBattle extends JavaPlugin {

    private static SkillBattle plugin = null;

    public static SkillBattle getPlugin() {
        if (plugin == null) {
            plugin = (SkillBattle) Bukkit.getPluginManager().getPlugin("SkillBattle");
        }
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new  InventoryClickListener(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(),this);
        getCommand("skillbattle").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
