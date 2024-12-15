package com.che1sBukkit.RollingDraw.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    static Random random = new Random();
    static String patternString = "\\((#([a-fA-F0-9]+))\\)(.*)";

    public static BaseComponent[] translateColor(String message) {
        ComponentBuilder componentBuilder = new ComponentBuilder();
        String[] strings = splitStrings(message);
        for (String str : strings) {
            String[] colorAndText = splitColor(str);
            if (colorAndText.length == 2) {
                componentBuilder.append(colorAndText[1]).color(ChatColor.of("#" + colorAndText[0]));
            } else {
                componentBuilder.append(str);
            }
        }
        return componentBuilder.create();
    }

    // 将(#颜色)文本分割为 颜色， 文本
    private static String[] splitColor(String str) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            String contentInBracket = matcher.group(2); //color

            String textAfterBracket = matcher.group(3); // text
            return new String[]{contentInBracket, textAfterBracket};
        }
        return new String[]{};
    }

    // 将字符串分割为(#颜色)文本 的数组
    private static String[] splitStrings(String raw) {
        String[] strings = raw.split("@");
        List<String> toReturn = new ArrayList<>();
        for (String str : strings) {
            if (str.startsWith("@")) {
                toReturn.add(str.substring(1));
            } else {
                toReturn.add(str);
            }
        }
        return toReturn.toArray(new String[]{});
    }

    public static void sendComponentMessage(CommandSender receiver, TextComponent message) {
        receiver.spigot().sendMessage(new TextComponent(message));
    }

    public static int randomId() {
        LocalDate currentDate = LocalDate.now();
        int randomFiveDigits = random.nextInt(90000) + 10000;
        return randomFiveDigits * 10000 + currentDate.getMonthValue() * 100 + currentDate.getDayOfMonth();
    }

    public static void writeToLogFile(String path, String[] logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (String message : logMessage) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
