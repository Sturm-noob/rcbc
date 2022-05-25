package me.sturm.rcbc;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class RcbcPlugin extends JavaPlugin{
	
	public void onEnable() {
		saveDefaultConfig();
		Configuration c = getConfig();
		for (String cmd : c.getKeys(false)) {
			List<ExecuteCmd> l = new ArrayList<>();
			ConfigurationSection s = c.getConfigurationSection(cmd);
			for (String b : s.getKeys(false)) 
				l.add(new ExecuteCmd(b, s.getBoolean(b+".player"), s.getDouble(b+".chance")));
			RandomExecutor ex = new RandomExecutor();
			RandomCmd.register(this, ex, cmd, l);
		}	
	}

}
