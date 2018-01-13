package it.mr_replete.staff.command.staffcommand;

import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.command.GCommand;
import it.mr_replete.staff.events.VerifEvent;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GVerif extends GCommand {

    public GVerif() {
        super("verif", false, "staff.verif");
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {

        if (args.length != 1){
            sender.sendMessage(ChatColor.RED + "/verif <player>");
            return;
        } else{
            Staffer staffer = null;
            try{
                staffer = StafferUtil.getStaffer(sender.getName());
            } catch(StafferNotFoundException e){
                e.printStackTrace();
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null){
                staffer.sendMessage(ChatColor.RED + "Player not found!");
                return;
            } else{
                Bukkit.getPluginManager().callEvent(new VerifEvent(staffer, target));
            }

        }

    }
}
