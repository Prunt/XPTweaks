package me.prunt.xptweaks;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    ConfigHandler config;

    @Override
    public void onEnable() {
	config = new ConfigHandler(this);
	new EventListener(this);
    }
}
