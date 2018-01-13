package it.mr_replete.staff.command.staffcommand;

import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.command.GCommand;
import it.mr_replete.staff.events.FreezeClickEvent;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GFreeze extends GCommand {

    public GFreeze() {
        super("freeze", false, "staff.freeze");
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {
        Staffer staffer = null;
        try{
            staffer = StafferUtil.getStaffer(sender.getName());
        } catch(StafferNotFoundException e){
            e.printStackTrace();
        }
        if (args.length != 1){
            staffer.sendMessage(ChatColor.RED + "/freeze <player>");
            return;
        } else {
            Player target = Bukkit.getPlayer(args[0]);
           if (target == null){
               staffer.sendMessage(ChatColor.RED + "Player not found!");
               return;
           } else{
               Bukkit.getPluginManager().callEvent(new FreezeClickEvent(staffer, target));
           }

        }

    }
}
