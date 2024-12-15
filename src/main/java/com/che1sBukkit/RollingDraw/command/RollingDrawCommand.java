package com.che1sBukkit.RollingDraw.command;

import com.che1sBukkit.RollingDraw.Roll;
import com.che1sBukkit.RollingDraw.instance.RollInstance;
import com.che1sBukkit.RollingDraw.util.Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RollingDrawCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // 获取命令的创建者，如果不是玩家则默认为控制台
        String creator;
        if (!(commandSender instanceof Player)) {
            creator = "console";
        } else {
            creator = commandSender.getName();
        }

        // 检查命令参数是否正确，必须是两个参数：数量和奖励
        if (strings.length != 2) {
            commandSender.sendMessage(ChatColor.RED + "用法：/RollingDraw <number> <reward>");
            return true;
        }

        // 将第一个参数转换为整数，表示抽奖的数量
        int amount = Integer.parseInt(strings[0]);
        // 第二个参数是奖励的描述
        String reward = strings[1];

        // 检查数量是否合法，必须大于0
        if (amount <= 0) {
            commandSender.sendMessage(ChatColor.RED + "人数必须大于0");
            return true;
        }

        // 检查数量是否超过在线玩家数量
        if (amount > Bukkit.getOnlinePlayers().size()) {
            commandSender.sendMessage(ChatColor.RED + "人数不能大于在线人数");
            return true;
        }

        // 创建一个抽奖实例，包含随机ID、创建者、奖励和数量
        RollInstance rollInstance = new RollInstance(Util.randomId(), creator, reward, amount);
        // 启动抽奖
        rollInstance.start();

        // 向命令发送者发送抽奖开始的消息
        commandSender.spigot().sendMessage(Util.translateColor(Roll.instance.languageConfig.ROLL_START));

        // 命令执行成功，返回false表示命令没有错误
        return false;
    }
}
