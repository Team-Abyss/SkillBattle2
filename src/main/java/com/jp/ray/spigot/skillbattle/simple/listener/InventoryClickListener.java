package com.jp.ray.spigot.skillbattle.simple.listener;

import com.jp.ray.spigot.skillbattle.api.*;
import com.jp.ray.spigot.skillbattle.simple.classes.Saber;
import com.jp.ray.spigot.skillbattle.simple.classes.SkillClasses;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("§a§b§c§1§2§3§r§lクラス選択")) {
            event.setCancelled(true);
            if (event.getClickedInventory() == event.getInventory()) {
                ItemStack itemStack = event.getCurrentItem();
                if (itemStack == null) {
                    return;
                }
                BattlePlayer player = BattlePlayerManager.getBattlePlayerOrNull((Player) event.getWhoClicked());
                if (player == null) {
                    event.getWhoClicked().closeInventory();
                    return;
                }
                if (itemStack.equals(SkillClasses.SABER.getSkillClass().getIcon())) {
                    player.setSkillClass(SkillClasses.SABER.getSkillClass());
                    player.setReady(true);
                    Game game = GameManager.getGame(player);
                    if (game != null) {
                        game.onPlayerReady(player);
                    }
                    player.getPlayer().closeInventory();
                }
            }
        }
    }
}
