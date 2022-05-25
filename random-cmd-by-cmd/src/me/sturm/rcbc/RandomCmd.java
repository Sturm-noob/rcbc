package me.sturm.rcbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

public class RandomCmd extends Command implements PluginIdentifiableCommand {
	
	private CommandExecutor ex;
	private Plugin pl;
	private List<ExecuteCmd> cmds = new ArrayList<>();
	private double sum = 0;

	private RandomCmd(String cmd, String[] al, String desc, String usage, CommandExecutor ex, Plugin pl) {
        super(cmd, desc, usage, Arrays.asList(al));
        this.ex = ex;
        this.pl = pl;
    }

	@Override
	public boolean execute(CommandSender arg0, String arg1, String[] arg2) {
		if (!this.testPermission(arg0)) return true;
		boolean b = this.ex.onCommand(arg0, this, arg1, arg2);
		if (!b) arg0.sendMessage(this.usageMessage);
		return b;
	}
	
	@Override
	public Plugin getPlugin() {return pl;}
	public List<ExecuteCmd> getCmds() {return cmds;}
	public double getSum() {return sum;}
	
	public static void register(Plugin pl, CommandExecutor ex, String cmd, List<ExecuteCmd> l) {
        RandomCmd rcmd = new RandomCmd(cmd, new String[] {}, "", "/"+cmd, ex, pl);
        rcmd.cmds = l;
        l.forEach(x -> rcmd.sum += x.getChance());
        CommandMap map = Bukkit.getCommandMap();
        map.register(pl.getName(), rcmd);
    }

}
