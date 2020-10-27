package com.jp.ray.spigot.skillbattle.api;

import com.jp.ray.spigot.skillbattle.simple.SimpleBattlePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BattlePlayerManager {

    private static List<BattlePlayer> players = new ArrayList<>();

    public static BattlePlayer getBattlePlayer(Player player) {
        BattlePlayer battlePlayer = getBattlePlayerOrNull(player);
        if (battlePlayer == null) {
            battlePlayer = new SimpleBattlePlayer(player);
            players.add(battlePlayer);
        }
        return battlePlayer;
    }

    public static BattlePlayer getBattlePlayerOrNull(Player player) {
        for (BattlePlayer bPlayer: players) {
            if (bPlayer.getPlayer().equals(player)) {
                return bPlayer;
            }
        }
        return null;
    }
}
