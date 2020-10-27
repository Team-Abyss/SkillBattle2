package com.jp.ray.spigot.skillbattle.simple;

import com.jp.ray.spigot.skillbattle.api.BattlePlayer;
import com.jp.ray.spigot.skillbattle.api.SkillClass;
import com.jp.ray.spigot.skillbattle.simple.classes.SkillClasses;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SimpleBattlePlayer implements BattlePlayer {

    private final Player player;
    private boolean isCoolTime = false;
    private boolean isReady = false;
    private boolean isInGame = false;
    private SkillClass skillClass = null;
    private int killCount = 0;
    private int deathCount = 0;

    public SimpleBattlePlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCoolTime() {
        return isCoolTime;
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    @Override
    public boolean isInGame() {
        return isInGame;
    }

    @Override
    public void setInGame(boolean inGame) {
        this.isInGame = inGame;
    }

    @Override
    public SkillClass getSkillClass() {
        return skillClass;
    }

    @Override
    public void setSkillClass(SkillClass skillClass) {
        this.skillClass = skillClass;
    }

    @Override
    public int getKillCount() {
        return killCount;
    }

    @Override
    public void setKillCount(int kill) {
        this.killCount = kill;
    }

    @Override
    public int incrementKillCount() {
        return killCount++;
    }

    @Override
    public int getDeathCount() {
        return deathCount;
    }

    @Override
    public void setDeathCount(int death) {
        this.deathCount = death;
    }

    @Override
    public int incrementDeathCount() {
        return deathCount++;
    }

    @Override
    public double getKDRate() {
        return (double) getKillCount()/getDeathCount();
    }

    @Override
    public void onDeath(BattlePlayer killer) {

    }

    @Override
    public void onKill(BattlePlayer victim) {

    }

    @Override
    public void openSelectInventory() {
        player.openInventory(getSelectInventory());
    }

    @Override
    public void initStatus() {
        if (skillClass != null) {
            Player player = getPlayer();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(skillClass.getHP());
            player.setHealthScale(20);
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(skillClass.getSpeed());
        }
    }

    @Override
    public void reset() {
        isReady = false;
        isInGame = false;
        skillClass = null;
        killCount = 0;
        deathCount = 0;
        isCoolTime = false;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.setHealthScale(20);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(Attribute.GENERIC_MOVEMENT_SPEED.ordinal());
    }

    private Inventory getSelectInventory() {
        Inventory inv = Bukkit.createInventory(null,27,"§a§b§c§1§2§3§r§lクラス選択");
        inv.setItem(2, SkillClasses.SABER.getSkillClass().getIcon());
        return inv;
    }
}
