package me.sturm.rcbc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class ExecuteCmd {
	
	private String cmd;
	private boolean is_player;
	private double chance;
	
	public ExecuteCmd(String cmd, boolean is_player, double chance) {
		this.cmd = cmd;
		this.is_player = is_player;
		this.chance = chance;
	}
	
	public double getChance() {return chance;}
	
	public void execute(Player p) {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) cmd = PlaceholderAPI.setPlaceholders(p, cmd);
		cmd = cmd.replaceAll("%player%", p.getName());
		Bukkit.dispatchCommand(is_player ? p : Bukkit.getConsoleSender(), cmd);
	}

}
