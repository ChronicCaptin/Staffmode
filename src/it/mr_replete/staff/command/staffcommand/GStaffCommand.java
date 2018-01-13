package it.mr_replete.staff.command.staffcommand;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.ItemsUtil;
import it.mr_replete.staff.Util.StafferManager;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.command.Toggle;
import it.mr_replete.staff.configuration.ConfUtil;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GStaffCommand extends Toggle {


    public GStaffCommand() {
        super("staffmode", false, "staff.staffmode");
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {
        if (args.length == 0) {
            try {
                Staffer staffer = StafferUtil.getStaffer(sender.getName());
                toggle(staffer);
            } catch (StafferNotFoundException e) {
                if (StafferUtil.isStaffer((Player) sender)) {
                    StafferUtil.fixUP(sender);
                    execute(sender, args, label);
                }
            }
        } else if (args.length != 1){
            sender.sendMessage(ChatColor.RED + "Wrong Usage!");
            return;
        } else if (args[0].equals("reload")){
            sender.sendMessage(ChatColor.GOLD + "[Staff] Loading components...");
            Staff.getInstance().reloadConfig();
            Staff.getInstance().saveDefaultConfig();
            Settings.setUp();
            StafferManager.fixOnRestartOrReload();
            for (Staffer staffer : Staff.getInstance().getStaffers()){
                staffer.setStaffMode(false);
                staffer.setGod(false);
            }
            sender.sendMessage(ChatColor.GREEN +"[Staff] Components Loaded! OK");
            sender.sendMessage(ChatColor.GREEN + "[Staff] Plugin Reloaded version: " + Bukkit.getPluginManager().getPlugin("Staff").getDescription().getVersion() + " Author: " + Bukkit.getPluginManager().getPlugin("Staff").getDescription().getAuthors().toString().replace("[","").replace("]",""));
        }

    }

    @Override
    protected void toggle(Staffer staffer) {

        if (staffer.isStaffMode()){
            staffer.setStaffMode(false);
            staffer.setVanish(false);
            staffer.setGod(false);
            staffer.getPlayer().setAllowFlight(false);
            staffer.getPlayer().setFlying(false);
            StafferManager.show(staffer.getPlayer());
            staffer.sendMessage(Settings.STAFFMODE_OFF);
            staffer.sendMessage(Settings.VANISH_OFFMSG);
            ItemsUtil.clear(staffer.getPlayer());
            if (Staff.getInstance().getLastinventories().containsKey(staffer)){
                staffer.getPlayer().getInventory().setContents(Staff.getInstance().getLastinventories().get(staffer));
                Staff.getInstance().getLastinventories().remove(staffer);
            }
        } else{
            staffer.heal();
            staffer.getPlayer().setAllowFlight(true);
            staffer.getPlayer().setFlying(true);
            staffer.setStaffMode(true);
            staffer.setVanish(true);
            staffer.setGod(true);
            Staff.getInstance().getLastinventories().put(staffer, staffer.getPlayer().getInventory().getContents());
            ItemsUtil.clear(staffer.getPlayer());
            ItemsUtil.addItems(staffer.getPlayer());
            if (Settings.VANISH_ACTIVE){
                StafferManager.hideOnlyPlayers(staffer.getPlayer());
            } else{
                StafferManager.hide(staffer.getPlayer());
            }
            staffer.sendMessage(Settings.STAFFMODE_ON);
            staffer.sendMessage(Settings.VANISH_ONMSG);
        }

    }
}
