package com.che1sBukkit.RollingDraw.config;

import com.che1sBukkit.RollingDraw.util.ConfigCursor;
import com.che1sBukkit.RollingDraw.util.ConfigUtil;

public class LanguageConfig {

    // 定义字符串常量
    public String ROLL_START = "@(#CCFF33)抽@(#CCFF56)奖开始!";
    public String TIME_LEFT = "剩余{time}秒开奖，可能是：{randomName}中奖哦!";
    public String CREATE_ROLL = "{creator}创建了一个抽奖，奖品是：{prize}，参与人数：{count}，中奖人数：{winnerCount}";
    public String PARTNER = "参与者名单如下: {names}，共{count}人";
    public String TIME_START = "现在开始倒计时10秒抽奖：";
    public String CONGRATULATION = "恭喜{winners}中奖！";
    public String COMMA = "、";
    public String NAME_COLOR = "@(#FFFFFF)";

    public void readConfig(ConfigUtil configUtil) {
        // 创建一个配置光标，用于读取配置文件
        ConfigCursor cursor = new ConfigCursor(configUtil, "");
        ROLL_START = cursor.getString("ROLL_START");
        TIME_LEFT = cursor.getString("TIME_LEFT");
        CREATE_ROLL = cursor.getString("CREATE_ROLL");
        PARTNER = cursor.getString("PARTNER");
        TIME_START = cursor.getString("TIME_START");
        CONGRATULATION = cursor.getString("CONGRATULATION");
        COMMA = cursor.getString("COMMA");
        NAME_COLOR = cursor.getString("NAME_COLOR");
    }
}
