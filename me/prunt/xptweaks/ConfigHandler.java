package me.prunt.xptweaks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;

public class ConfigHandler {
    private Main main;

    Random rand = new Random();

    /* Blocks */
    List<Material> mining = new ArrayList<>();

    ConfigHandler(Main main) {
	this.main = main;

	main.saveDefaultConfig();

	loadConfig();
    }

    void loadConfig() {
	main.reloadConfig();

	mining.clear();
	for (String key : main.getConfig().getConfigurationSection("mining").getKeys(false)) {
	    Material ma = Material.valueOf(key);
	    mining.add(ma);
	}
    }

    int getXP(String type, String subtype) {
	int min = main.getConfig().getInt(type + "." + subtype + ".min");
	int max = main.getConfig().getInt(type + "." + subtype + ".max");

	int amount = rand.nextInt(max - min);
	amount += min;

	return amount;
    }
}
