package com.jp.ray.spigot.skillbattle.simple.listener;

import com.jp.ray.spigot.skillbattle.SkillBattle;
import com.jp.ray.spigot.skillbattle.api.BattlePlayer;
import com.jp.ray.spigot.skillbattle.api.BattlePlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().getName().equals("§a§b§c§1§2§3§r§lクラス選択")) {
            BattlePlayer player = BattlePlayerManager.getBattlePlayerOrNull((Player) event.getPlayer());
            if (player != null) {
                if (player.getSkillClass() == null) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.openSelectInventory();
                        }
                    }.runTaskLater(SkillBattle.getPlugin(),5);
                }
            }
        }
    }

}
