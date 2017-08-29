package me.prunt.xptweaks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class EventListener implements Listener {
    private Main main;

    EventListener(Main main) {
	this.main = main;

	main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent e) {
	Player p = (Player) e.getWhoClicked();
	int j = e.getCurrentItem().getAmount();
	int xp = 0;

	for (int i = 1; i < j; i++) {
	    xp += main.config.getXP("actions", "crafting");
	}

	p.giveExp(xp);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
	Player p = e.getPlayer();

	p.giveExp(main.config.getXP("actions", "eating"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
	Player p = e.getPlayer();
	Material ma = e.getBlock().getType();

	if (main.config.mining.contains(ma))
	    p.giveExp(main.config.getXP("mining", ma.name()));
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent e) {
	Player p = e.getPlayer();

	p.giveExp(main.config.getXP("actions", "advancement"));
    }
}
