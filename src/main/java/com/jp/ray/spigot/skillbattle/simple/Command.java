package com.jp.ray.spigot.skillbattle.simple;

import com.jp.ray.spigot.skillbattle.api.BattlePlayerManager;
import com.jp.ray.spigot.skillbattle.api.Game;
import com.jp.ray.spigot.skillbattle.api.GameManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Game game = GameManager.createNewGame(args[0]);
        System.out.println (((Player) sender).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue());
        game.addPlayer(BattlePlayerManager.getBattlePlayer((Player) sender));
        return true;
    }
}
