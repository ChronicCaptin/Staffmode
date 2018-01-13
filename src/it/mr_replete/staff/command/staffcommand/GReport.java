package it.mr_replete.staff.command.staffcommand;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.Sender;
import it.mr_replete.staff.command.GCommand;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.TimeUnit;

public class GReport extends GCommand {

    public GReport() {
        super("report", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {

        if (args.length != 2){
            sender.sendMessage(ChatColor.DARK_RED + "Error: "  + ChatColor.RED + "/report <player> <reason>");
            return;
        } else {

            if (Staff.getInstance().getReportdelay().containsKey(Bukkit.getPlayer(sender.getName()).getUniqueId()) && Staff.getInstance().getReportdelay().get(Bukkit.getPlayer(sender.getName()).getUniqueId()) > System.currentTimeMillis()){
                sender.sendMessage(Settings.REPORT_DELAY_MSG.replace("%seconds%", String.valueOf((int)TimeUnit.MILLISECONDS.toSeconds((Staff.getInstance().getReportdelay().get(Bukkit.getPlayer(sender.getName()).getUniqueId())-System.currentTimeMillis())))));
                return;
            }

            String possiblePlayer = args[0];
            String reason = args[1];

            if (Bukkit.getPlayer(possiblePlayer) != null){

                Staff.getInstance().getReportdelay().put(Bukkit.getPlayer(sender.getName()).getUniqueId(), System.currentTimeMillis() + Settings.REPORT_DELAY * 1000);

                for (String messages : Settings.REPORT_FORMAT){
                    sender.sendMessage(messages.replace("%reporter%", sender.getName()).replace("%reported%", possiblePlayer).replace("%reason", reason));
                }
                sender.sendMessage(ChatColor.GREEN + "Report sent successfully!");
            } else{
                sender.sendMessage(ChatColor.RED + "Player must be online!");
                return;
            }

        }

    }
}
