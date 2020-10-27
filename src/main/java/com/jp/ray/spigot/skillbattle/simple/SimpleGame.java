package com.jp.ray.spigot.skillbattle.simple;

import com.jp.ray.spigot.skillbattle.SkillBattle;
import com.jp.ray.spigot.skillbattle.api.BattlePlayer;
import com.jp.ray.spigot.skillbattle.api.Game;
import com.jp.ray.spigot.skillbattle.api.GameManager;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleGame implements Game {
    //準備が完了したら自動で開始

    private final String name;
    private final List<BattlePlayer> players = new ArrayList<>();
    private BukkitRunnable runnable = null;

    private boolean isStarted = false;
    private int timeLimit = 30;
    private int time = 0;

    public SimpleGame(String name) {
        this.name = name;
    }

    @Override
    public List<BattlePlayer> getPlayers() {
        return players;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void onPlayerReady(BattlePlayer player) {
        for (BattlePlayer player1: players) {
            if (! player1.isReady()) {
                return;
            }
        }
        start();
    }

    @Override
    public int getMaxMember() {
        return -1;
    }

    @Override
    public boolean addPlayer(BattlePlayer player) {
        if (GameManager.getGame(player) != null) {
            throw new  IllegalArgumentException("this BattlePlayer is already belong to another Game");
        } else {
            player.openSelectInventory();
            return players.add(player);
        }
    }

    @Override
    public boolean removePlayer(BattlePlayer player) {
        return players.remove(player);
    }

    @Override
    public int getTimeLimit() {
        return timeLimit;
    }

    @Override
    public boolean setTimeLimit(int timeLimit) {
        if (! isStarted) {
            this.timeLimit = timeLimit;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public boolean start() {
        System.out.println("start");
        if (! isStarted) {
            isStarted = true;
            for (BattlePlayer player1: players) {
                player1.setInGame(true);
                player1.initStatus();
            }
            runnable = new BukkitRunnable() {
                private int time2 = -5;
                @Override
                public void run() {
                    if (time2 < 0) {
                        players.stream().map(BattlePlayer::getPlayer).forEach(it -> it.sendTitle("開始 "+-time2+"秒前",null,0,20,0));
                    } if (time2 == 0) {
                        players.stream().map(BattlePlayer::getPlayer).forEach(it -> it.sendTitle("対戦開始",null,0,20,0));
                    }
                    if (timeLimit - time <= 5) {
                        if (timeLimit == time) {
                            players.stream().map(BattlePlayer::getPlayer).forEach(it -> it.sendTitle("終了",null,0,20,0));
                            end();
                            cancel();
                            return;
                        } else {
                            players.stream().map(BattlePlayer::getPlayer).forEach(it -> it.playSound(it.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON,1,2));
                        }
                    }
                    time2++;
                    time = time2;
                }
            };
            runnable.runTaskTimer(SkillBattle.getPlugin(),0,20);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean end() {
        if (isStarted) {
            isStarted = false;
            runnable.cancel();
            runnable = null;
            players.forEach(BattlePlayer::reset);
            GameManager.removeGame(this);
            return true;
        } else {
            return false;
        }
    }

    private List<BattlePlayer> sort() {
        return players.stream().filter(BattlePlayer::isReady).sorted((a,b) -> {
          int i = a.getKillCount() - b.getKillCount();
          if (i == 0) {
              int i2 = b.getDeathCount() - a.getDeathCount();
              if (i2 == 0) {
                  double i3 = a.getKDRate() - b.getKDRate();
                  if (i3 > 0) {
                      return 1;
                  } else if (i3 < 0) {
                      return -1;
                  } else {
                      return 0;
                  }
              } else {
                  return i2;
              }
          } else {
              return i;
          }
        }).collect(Collectors.toList());
    }

}
