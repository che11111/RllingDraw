package com.che1sBukkit.RollingDraw.instance;

import com.che1sBukkit.RollingDraw.Roll;
import com.che1sBukkit.RollingDraw.util.Util;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RollInstance {

    Roll roll = Roll.instance;

    @Getter final int id;
    @Getter final String creator;
    @Getter final String reward;
    @Getter final int amount;
    final List<String> members
            = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    @Getter @Setter String[] selectedMember;
    String lastSelect;

    int taskId;

    /**
     * 启动抽奖任务
     */
    public void start() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Roll.instance, new RollRunnable(), 0, 20);
    }

    /**
     * 进行抽奖
     */
    public void roll() {
        this.selectedMember = new String[amount];
        List<String> clone= new java.util.ArrayList<>(members.stream().toList());
        for (int i = 0; i < amount; i++) {
            int ran = (int)(Math.random() * clone.size());
            selectedMember[i] = clone.get(ran);
            clone.remove(ran);
        }
        writeToLog();
    }

    /**
     * 将抽奖结果写入日志文件
     */
    public void writeToLog() {
        Util.writeToLogFile(Roll.instance.getDataFolder().getAbsolutePath() + "/log/" + id + ".txt", new String[]{
                "时间：" + new Date(),
                "抽奖者：" + creator,
                "名单：" + getRawNameList(members.toArray(new String[]{})),
                "奖品：" + reward,
                "人数：" + amount,
                "中奖者：" + getRawNameList(selectedMember)
        });
    }

    /**
     * 获取原始名称列表
     *
     * @param names 名称数组
     * @return 原始名称列表字符串
     */
    private String getRawNameList(String[] names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]);
            if (i != names.length - 1) {
                sb.append("、 ");
            }
        }
        return sb.toString();
    }

    /**
     * 获取格式化名称列表
     *
     * @param names 名称数组
     * @return 格式化名称列表字符串
     */
    private String getNameList(String[] names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            sb.append("@" + roll.languageConfig.NAME_COLOR + names[i]);
            if (i != names.length - 1) {
                sb.append(roll.languageConfig.COMMA);
            }
        }
        return sb.toString();
    }

    /**
     * 抽奖任务的执行逻辑
     */
    public class RollRunnable implements Runnable {

        public int time = 10;

        /**
         * 向所有在线玩家广播消息
         *
         * @param msg 消息内容
         */
        void boardcast(String msg) {
            Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(Util.translateColor(msg)));
        }

        /**
         * 随机抽取一个在线玩家并广播消息
         */
        void boardcastRandom() {
            String ranName = members.get((int)(Math.random() * members.size()));
            if (ranName.equals(lastSelect) && members.size() > 1) {
                boardcastRandom();
                return;
            }
            lastSelect = ranName;
            boardcast(roll.languageConfig.TIME_LEFT
                    .replace("{time}", String.valueOf(time)).replace("{randomName}", ranName));
        }

        /**
         * 当任务被调度时执行的操作
         */
        @Override
        public void run() {
            if (time <0) {
                Bukkit.getScheduler().cancelTask(taskId);
                return;
            }
            if (time == 10) {
                boardcast(roll.languageConfig.CREATE_ROLL
                        .replace("{creator}", creator)
                        .replace("{prize}", reward).replace("{count}", String.valueOf(members.size()))
                        .replace("{winnerCount}", String.valueOf(amount)));
                boardcast(roll.languageConfig.PARTNER
                        .replace("{names}", getNameList(members.toArray(new String[]{})))
                        .replace("{count}", String.valueOf(members.size())));
                boardcast(roll.languageConfig.TIME_START);
                boardcast("");
                boardcastRandom();
            }
            if (time == 5 || time == 8 || time <= 3) {
                boardcastRandom();
            }
            if (time == 0) {
                roll();
                boardcast(roll.languageConfig.CONGRATULATION
                        .replace("{winners}", getNameList(selectedMember)));
                Bukkit.getScheduler().cancelTask(taskId);
                return;
            }
            time--;
        }
    }

}
