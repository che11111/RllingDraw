package com.che1sBukkit.RollingDraw;


import com.sun.security.auth.login.ConfigFile;
import com.che1sBukkit.RollingDraw.command.RollingDrawCommand;
import com.che1sBukkit.RollingDraw.config.LanguageConfig;
import com.che1sBukkit.RollingDraw.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Roll extends JavaPlugin {

    public static Roll instance;
    public LanguageConfig languageConfig;

    @Override
    public void onEnable() {
        instance = this;
        initFile();
        registerCommand();
        getLogger().info("Roll plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Roll plugin disabled.");
    }

    void registerCommand() {
        getCommand("spotdraw").setExecutor(new RollingDrawCommand());
    }

    void initFile() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File logFolder = new File(getDataFolder(), "log");
        if (!logFolder.exists()) {
            logFolder.mkdir();
        }
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            saveDefaultConfig();
        }
        languageConfig = new LanguageConfig();
        languageConfig.readConfig(new ConfigUtil(this, "config.yml"));
    }

}
