package it.mr_replete.staff.command.staffcommand;

import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.command.GCommand;
import it.mr_replete.staff.events.StaffChatEvent;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class GStaffChat extends GCommand {

    public GStaffChat() {
        super("staffchat", false, "staff.chat");
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {

        if (args.length != 0){
            try{
                Staffer staffer = StafferUtil.getStaffer(sender.getName());
                Bukkit.getPluginManager().callEvent(new StaffChatEvent(staffer, Arrays.toString(args).replace("[", "").replace("]", "")));
            } catch(StafferNotFoundException e){
                if (StafferUtil.isStaffer((Player)sender)) {
                    StafferUtil.fixUP(sender);
                    execute(sender, args, label);
                }
            }
        } else{
            sender.sendMessage(Settings.SYNTAX_ERROR);
        }

    }
}
