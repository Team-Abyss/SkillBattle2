package com.jp.ray.spigot.skillbattle.api;

import java.util.List;

public interface Game {

    List<BattlePlayer> getPlayers();

    String getName();

    //準備完了したとき
    void onPlayerReady(BattlePlayer player);

    //-1は制限なし
    int getMaxMember();

    boolean addPlayer(BattlePlayer player);

    boolean removePlayer(BattlePlayer player);

    int getTimeLimit();

    boolean setTimeLimit(int timeLimit);

    //デフォは0
    int getTime();

    boolean isStarted();

    boolean start();

    boolean end();

}
