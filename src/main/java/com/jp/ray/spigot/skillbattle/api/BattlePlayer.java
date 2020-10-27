package com.jp.ray.spigot.skillbattle.api;

import org.bukkit.entity.Player;

public interface BattlePlayer {

    Player getPlayer();

    boolean isCoolTime();

    //準備完了
    boolean isReady();

    void setReady(boolean isReady);

    boolean isInGame();

    void setInGame(boolean inGame);

    SkillClass getSkillClass();

    void setSkillClass(SkillClass skillClass);

    int getKillCount();

    void setKillCount(int kill);

    int incrementKillCount();

    int getDeathCount();

    void setDeathCount(int death);

    int incrementDeathCount();

    double getKDRate();

    void onDeath(BattlePlayer killer);

    void onKill(BattlePlayer victim);

    void openSelectInventory();

    void initStatus();

    void reset();
}
