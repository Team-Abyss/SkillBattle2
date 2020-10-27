package com.jp.ray.spigot.skillbattle.api;

public interface Skill {

    int getCoolTime();

    boolean use(BattlePlayer player);
}
