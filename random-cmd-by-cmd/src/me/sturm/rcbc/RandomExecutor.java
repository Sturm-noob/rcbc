package me.sturm.rcbc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (!(arg1 instanceof RandomCmd)) return false;
		RandomCmd cmd = (RandomCmd) arg1;
		if (!arg0.hasPermission("rcbc."+arg1.getName())) {
			arg0.sendMessage(ChatColor.RED+"Нет прав");
			return true;
		}
		if (!(arg0 instanceof Player)) {
			arg0.sendMessage(ChatColor.RED + "Only player cmd");
			return true;
		}
		double r = Math.random() * cmd.getSum();
		double s = 0;
		ExecuteCmd ret = null;
		for (ExecuteCmd c : cmd.getCmds()) {
			if (s < r) ret = c;
			else break;
			s += c.getChance();
		}
		Player p = (Player) arg0;
		ret.execute(p);
		return true;
	}

}
