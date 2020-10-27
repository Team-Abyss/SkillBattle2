package com.jp.ray.spigot.skillbattle.api;

import com.jp.ray.spigot.skillbattle.simple.SimpleGame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static List<Game> games = new ArrayList<>();

    public static Game createNewGame(String name) {
        Game game = new SimpleGame(name);
        games.add(game);
        return game;
    }

    public static Game getGame(String name) {
        for (Game game: games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    public static Game getGame(BattlePlayer player) {
        for (Game game: games) {
            if (game.getPlayers().contains(player)) {
                return game;
            }
        }
        return null;
    }

    public static boolean removeGame(String name) {
        for (Game game: games) {
            if (game.getName().equals(name)) {
                games.remove(game);
                return true;
            }
        }
        return false;
    }

    public static boolean removeGame(Game game) {
        return games.remove(game);
    }

    public static boolean start(String name) {
        for (Game game: games) {
            if (game.getName().equals(name)) {
                return game.start();
            }
        }
        return false;
    }

    public static boolean end(String name) {
        for (Game game: games) {
            if (game.getName().equals(name)) {
                return game.end();
            }
        }
        return false;
    }
}
